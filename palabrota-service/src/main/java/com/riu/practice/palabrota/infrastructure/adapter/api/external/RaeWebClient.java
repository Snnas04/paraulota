package com.riu.practice.palabrota.infrastructure.adapter.api.external;

import com.riu.practice.palabrota.domain.exception.InfrastructureException;
import com.riu.practice.palabrota.domain.exception.InfrastructureExceptionCode;
import com.riu.practice.palabrota.domain.port.service.RaePortAdapter;
import com.riu.practice.palabrota.infrastructure.adapter.api.external.dto.RaeDataDto;
import com.riu.practice.palabrota.infrastructure.adapter.api.external.dto.RaeDataWordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class RaeWebClient implements RaePortAdapter {
    @Qualifier("securedRestTemplate")
    private final RestTemplate restTemplate;
    private final String raeBasePath = "https://rae-api.com/api/";

    @Override
    public String getRandomWord(int max, int min) {
        ResponseEntity<RaeDataWordDto> response = this.restTemplate.exchange(
                getRandomUri(String.valueOf(max), String.valueOf(min)),
                HttpMethod.GET,
                getHeaders(),
                new ParameterizedTypeReference<>() {
                });

        if (!response.getStatusCode().is2xxSuccessful())
            throw new InfrastructureException("Failed call to rae-api.com", InfrastructureExceptionCode.INTERNAL_SERVER_ERROR);

        if (response.getBody() == null || response.getBody().data() == null || response.getBody().data().word() == null)
            throw new InfrastructureException("No word found in the response", InfrastructureExceptionCode.INTERNAL_SERVER_ERROR);

        String word = response.getBody().data().word();

        if (!word.matches("^[a-záéíóúüñ]+$"))
            return getRandomWord(max, min);

        return word;
    }

    @Override
    public String getDailyGame() {
        ResponseEntity<RaeDataWordDto> response = this.restTemplate.exchange(
                getDailyUri(),
                HttpMethod.GET,
                getHeaders(),
                new ParameterizedTypeReference<>() {
                });

        if (!response.getStatusCode().is2xxSuccessful())
            throw new InfrastructureException("Failed call to rae-api.com", InfrastructureExceptionCode.INTERNAL_SERVER_ERROR);

        return response.getBody().data().word().split(",")[0];
    }

    @Override
    public RaeDataDto getWord(String word) {
        try {
            ResponseEntity<RaeDataWordDto> response = this.restTemplate.exchange(
                    getWordUri(word),
                    HttpMethod.GET,
                    getHeaders(),
                    new ParameterizedTypeReference<>() {
                    });

            if (!response.getStatusCode().is2xxSuccessful())
                throw new InfrastructureException("Failed call to rae-api.com", InfrastructureExceptionCode.INTERNAL_SERVER_ERROR);

            return response.getBody().data();
        } catch (HttpClientErrorException.NotFound e) {
            log.error("Word {} not found: {}", word, e.getMessage());
            throw new InfrastructureException("Word not found", InfrastructureExceptionCode.NOT_FOUND);
        } catch (HttpClientErrorException e) {
            log.error("Error fetching word {}: {}", word, e.getMessage());
            throw new InfrastructureException("Failed call to rae-api.com", InfrastructureExceptionCode.INTERNAL_SERVER_ERROR);
        }
    }

    private static HttpEntity<String> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, "es_ES");

        return new HttpEntity<>(headers);
    }

    private URI getDailyUri() {
        return UriComponentsBuilder
                .fromHttpUrl(raeBasePath)
                .path("daily")
                .build().toUri();
    }

    private URI getWordUri(String word) {
        return UriComponentsBuilder
                .fromHttpUrl(raeBasePath)
                .path("words")
                .pathSegment(word)
                .build().toUri();
    }

    private URI getRandomUri(String max, String min) {
        return UriComponentsBuilder
                .fromHttpUrl(raeBasePath)
                .path("random")
                .queryParam("max_length", max)
                .queryParam("min_length", min)
                .build().toUri();
    }
}
