package com.riu.practice.palabrota.domain.service;


import com.riu.practice.palabrota.domain.model.WordModel;
import com.riu.practice.palabrota.domain.port.service.RaePortAdapter;
import com.riu.practice.palabrota.infrastructure.adapter.api.external.dto.RaeDataDto;
import com.riu.practice.palabrota.infrastructure.adapter.api.external.mapper.RaeWordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaeService {
    private final RaePortAdapter portAdapter;
    private final RaeWordMapper mapper;

    public String getRandomWord(int max, int min) {
        return portAdapter.getRandomWord(max, min);
    }

    public String getDailyGame() {
        return portAdapter.getDailyGame();
    }

    public WordModel getWord(String word) {
        RaeDataDto data = portAdapter.getWord(word);

        return mapper.toModel(data);
    }
}
