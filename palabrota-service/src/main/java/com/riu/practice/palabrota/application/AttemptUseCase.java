package com.riu.practice.palabrota.application;

import com.riu.practice.palabrota.domain.model.AttemptResultModel;
import com.riu.practice.palabrota.domain.model.DoAttemptModel;
import com.riu.practice.palabrota.domain.model.GameModel;
import com.riu.practice.palabrota.domain.port.repository.GameResponseRepositoryPort;
import com.riu.practice.palabrota.domain.port.service.AttemptService;
import com.riu.practice.palabrota.domain.service.WordAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttemptUseCase implements AttemptService {
    private final GameResponseRepositoryPort repositoryPort;
    private final WordAlgorithm algorithm;

    @Override
    public AttemptResultModel check(DoAttemptModel attempt) {
        GameModel game = repositoryPort.findById(attempt.uuid());
        String[] objectiveLetters = normalize(game.word()).split("");
        String[] attemptLetters = normalize(attempt.word()).split("");

        return new AttemptResultModel(
                attempt.uuid(),
                attempt.word(),
                game.gameSettings().attempts() - attempt.attempt(),
                algorithm.compareLetters(objectiveLetters, attemptLetters));
    }

    private String normalize(String word) {
        String temp = word.replace("ñ", "__enie__");
        temp = java.text.Normalizer.normalize(temp, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();

        return temp.replace("__enie__", "ñ");
    }
}
