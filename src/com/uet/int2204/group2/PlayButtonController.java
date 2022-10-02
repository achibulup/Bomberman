package com.uet.int2204.group2;

import com.uet.int2204.group2.component.GameState;

import java.io.IOException;

import javafx.fxml.FXML;

public class PlayButtonController {

    @FXML
    private void switchToGame() throws IOException {
      GameState game = new GameState();
      Bomberman.setRoot(game.getRoot());
      Bomberman.setInputHandlers(game.getInputHandlers());
      game.start();
    }
}
