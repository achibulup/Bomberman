package com.uet.int2204.group2.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.utils.Direction;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyBoardPlayerController extends KeyBoardEntityController<Player> {
  private List<Direction> directions = new ArrayList<>();
  private boolean placeBomb = false;

  public KeyBoardPlayerController() {
    super();
  }

  public KeyBoardPlayerController(Collection<EventHandler<KeyEvent>> handlerList) {
    super(handlerList);
  }

  @Override
  public void control(Player player) {
    if (this.placeBomb) {
      player.placeBomb();
    }
    if (this.directions.isEmpty()) {
      player.setDirection(Direction.NONE);
    } else {
      player.setDirection(this.directions.get(directions.size() - 1));
    }
  }

  @Override
  public void handle(KeyEvent event) {
    if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
      Direction dir = Direction.NONE;
      switch (event.getCode()) {
        case UP:
          dir = Direction.UP;
          break;
        case DOWN:
          dir = Direction.DOWN;
          break;
        case LEFT:
          dir = Direction.LEFT;
          break;
        case RIGHT:
          dir = Direction.RIGHT;
          break;
        case SPACE:
          placeBomb = true;
          break;
        default:
      }
      if (dir != Direction.NONE && !this.directions.contains(dir)) {
        this.directions.add(dir);
      }
    }
    if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
      Direction dir = Direction.NONE;
      switch (event.getCode()) {
        case UP:
          dir = Direction.UP;
          break;
        case DOWN:
          dir = Direction.DOWN;
          break;
        case LEFT:
          dir = Direction.LEFT;
          break;
        case RIGHT:
          dir = Direction.RIGHT;
          break;
        case SPACE:
          placeBomb = false;
          break;
        default:
      }
      this.directions.remove(dir);
    }
  }
}
