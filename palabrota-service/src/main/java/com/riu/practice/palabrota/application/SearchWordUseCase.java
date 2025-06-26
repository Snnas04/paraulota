package com.riu.practice.palabrota.application;

import com.riu.practice.palabrota.domain.model.WordModel;
import com.riu.practice.palabrota.domain.port.service.SearchWordService;
import com.riu.practice.palabrota.domain.service.RaeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchWordUseCase implements SearchWordService {
    private final RaeService raeService;

    @Override
    public WordModel getWord(String word) {
        WordModel wordDefinition =  raeService.getWord(word);
        return wordDefinition;
    }
}
