package com.riu.practice.palabrota.infrastructure.adapter.api.internal.controller;

import com.riu.practice.palabrota.domain.port.service.AttemptService;
import com.riu.practice.palabrota.domain.port.service.CreateGameService;
import com.riu.practice.palabrota.domain.port.service.SearchWordService;
import com.riu.practice.palabrota.infrastructure.adapter.api.internal.dto.*;
import com.riu.practice.palabrota.infrastructure.adapter.api.internal.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class PalabrotaController implements WordApi {
    private final CreateGameService gameService;
    private final GameLevelsMapper levelsMapper;
    private final GameMapper gameMapper;

    private final AttemptService attemptService;
    private final DoAttemptMapper attemptMapper;
    private final AttemptResultMapper attemptResultMapper;

    private final SearchWordService wordService;
    private final SearchWordMapper wordMapper;

    @Override
    public ResponseEntity<NewGameResponseDto> createNewGame(NewGameRequestDto requestDto) {
        log.info("Creating new game with levels: {}", requestDto);
        return ResponseEntity.ok(gameMapper.toDto(gameService.createNewGame(levelsMapper.toModel(requestDto))));
    }

    @Override
    public ResponseEntity<NewGameResponseDto> getDailyGame() {
        log.info("Start daily game");
        return ResponseEntity.ok(gameMapper.toDto(gameService.createDaily()));
    }

    @Override
    public ResponseEntity<ReturnAttemptDto> doAttempt(DoAttemptDto attemptDto) {
        log.info("Processing attempt: {}", attemptDto);
        return ResponseEntity.ok(attemptResultMapper.toDto(attemptService.check(attemptMapper.toModel(attemptDto))));
    }

    @Override
    public ResponseEntity<WordSearchDto> getWord(String word) {
        log.info("Searching for word: {}", word);
        return ResponseEntity.ok(wordMapper.toDto(wordService.getWord(word)));
    }
}
