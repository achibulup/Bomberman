package com.uet.int2204.group2.controller;

import com.uet.int2204.group2.entity.Enemy;
import com.uet.int2204.group2.utils.Direction;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.Collection;

public class KeyboardEnemyController extends KeyBoardEntityController<Enemy> {
  private Direction direction = Direction.NONE;

  public KeyboardEnemyController() {
    super();
  }

  public KeyboardEnemyController(Collection<EventHandler<KeyEvent>> handlerList) {
    super(handlerList);
  }

  @Override
  public void control(Enemy enemy) {
    enemy.setDirection(this.direction);
    this.direction = Direction.NONE;
  }

  @Override
  public void handle(KeyEvent event) {
    if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
      switch (event.getCode()) {
        case W:
          this.direction = Direction.UP;
          break;
        case S:
          this.direction = Direction.DOWN;
          break;
        case A:
          this.direction = Direction.LEFT;
          break;
        case D:
          this.direction = Direction.RIGHT;
          break;
        case Z:
          this.direction = Direction.NONE;
          break;
        default:
      }
    }
  }
}
