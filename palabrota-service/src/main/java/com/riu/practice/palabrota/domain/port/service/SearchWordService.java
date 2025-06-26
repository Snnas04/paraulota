package com.riu.practice.palabrota.domain.port.service;

import com.riu.practice.palabrota.domain.model.WordModel;

public interface SearchWordService {
    WordModel getWord(String word);
}
