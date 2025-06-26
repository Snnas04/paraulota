package com.riu.practice.palabrota.domain.port.service;

import com.riu.practice.palabrota.infrastructure.adapter.api.external.dto.RaeDataDto;

public interface RaePortAdapter {
    String getRandomWord(int max, int min);
    String getDailyGame();
    RaeDataDto getWord(String word);
}
