package com.uet.int2204.group2.component;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardLevelController implements GameStateTrigger, EventHandler<KeyEvent> {
  private enum LevelChange {
    NEXT, PREV, NONE
  }

  private LevelChange levelChange = LevelChange.NONE;

  @Override 
  public void handle(KeyEvent event) {
    if (event.getEventType() == KeyEvent.KEY_PRESSED) {
      if (event.getCode() == KeyCode.OPEN_BRACKET) {
        levelChange = LevelChange.PREV;
      }
      if (event.getCode() == KeyCode.CLOSE_BRACKET) {
        levelChange = LevelChange.NEXT;
      }
    }
  }

  @Override
  public boolean checkCondition(GameState target) {
    return this.levelChange != LevelChange.NONE;
  }

  @Override
  public void activate(GameState target) {
    if (this.levelChange == LevelChange.PREV) {
      target.prevLevel();
    }
    if (this.levelChange == LevelChange.NEXT) {
      target.nextLevel();
    }
    this.levelChange = LevelChange.NONE;
  }
}