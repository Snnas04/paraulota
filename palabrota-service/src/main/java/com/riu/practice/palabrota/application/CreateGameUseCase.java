package com.riu.practice.palabrota.application;

import com.riu.practice.palabrota.domain.model.GameLevelsModel;
import com.riu.practice.palabrota.domain.model.GameModel;
import com.riu.practice.palabrota.domain.port.service.CreateGameService;
import com.riu.practice.palabrota.domain.service.RaeService;
import com.riu.practice.palabrota.domain.service.SaveGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateGameUseCase implements CreateGameService {
    private final RaeService raeService;
    private final SaveGameService saveGameService;

    @Override
    public GameModel createNewGame(GameLevelsModel model) {
        String wordLength = saveGameService.getWordLength(model.word());
        String word = raeService.getRandomWord(Integer.parseInt(wordLength.split("-")[0]), Integer.parseInt(wordLength.split("-")[1]));

        return saveGameService.saveGame(word, model.attempts());
    }

    @Override
    public GameModel createDaily() {
        return saveGameService.saveGame(raeService.getDailyGame(), 2);
    }
}
