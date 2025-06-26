package com.riu.practice.palabrota.domain.service;

import com.riu.practice.palabrota.domain.model.GameModel;
import com.riu.practice.palabrota.domain.model.GameSettingsModel;
import com.riu.practice.palabrota.domain.port.repository.GameResponseRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveGameService {
    private final GameResponseRepositoryPort repositoryPort;

    @Value("${difficulties-levels.letters}")
    private String wordLength;

    @Value("${difficulties-levels.attempts}")
    private String attemptsNumber;

    public GameModel saveGame(String word, int attempts) {
        GameModel game = new GameModel(null, word, new GameSettingsModel(word.length(), getAttemptsNumber(attempts)));

        UUID uuid = repositoryPort.save(game);
        log.info("The objective word is {}", word);

        return new GameModel(uuid, word, new GameSettingsModel(word.length(), getAttemptsNumber(attempts)));
    }

    public String getWordLength(int word) {
        return switch (word) {
            case 1 -> wordLength.split(",")[0];
            case 2 -> wordLength.split(",")[1];
            case 3 -> wordLength.split(",")[2];
            default -> wordLength.split(",")[3];
        };
    }

    private int getAttemptsNumber(int attempts) {
        return switch (attempts) {
            case 1 -> Integer.parseInt(attemptsNumber.split(",")[0]);
            case 2 -> Integer.parseInt(attemptsNumber.split(",")[1]);
            default -> Integer.parseInt(attemptsNumber.split(",")[3]);
        };
    }

}
