package com.riu.practice.palabrota.domain.port.service;

import com.riu.practice.palabrota.domain.model.GameLevelsModel;
import com.riu.practice.palabrota.domain.model.GameModel;

public interface CreateGameService {
    GameModel createNewGame(GameLevelsModel model);
    GameModel createDaily();
}
