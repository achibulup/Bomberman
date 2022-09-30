package com.uet.int2204.group2.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.entity.MovableEntity.Direction;

import javafx.scene.input.KeyEvent;

public class KeyBoardPlayerController extends KeyBoardEntityController<Player> {
  public List<Direction> directions = new ArrayList<>();
  boolean placeBomb = false;

  public KeyBoardPlayerController() {
    super();
  }

  public KeyBoardPlayerController(Collection<KeyInputHandler> handlerList) {
    super(handlerList);
  }

  @Override public void control(Player player, World world) {
    if (this.placeBomb) {
      player.placeBomb(world);
    }
    if (this.directions.isEmpty()) {
      player.move(Direction.NONE);
    } else {
      player.move(this.directions.get(directions.size() - 1));
    }
  }

  @Override public void onKeyPressed(KeyEvent event) {
    System.out.println(event.getCode() + " pressed");
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

  @Override public void onKeyReleased(KeyEvent event) {
    System.out.println(event.getCode() + " released");
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