package com.uet.int2204.group2;

import com.uet.int2204.group2.component.GameCanvas;

import java.io.IOException;

import javafx.fxml.FXML;

public class Controller {

    @FXML
    private void switchToGame() throws IOException {
      GameCanvas game = new GameCanvas();
      Bomberman.setRoot(game.getRoot());
      game.start();
    }
}
