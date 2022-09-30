package com.uet.int2204.group2.controller;

import java.util.Collection;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.entity.MovableEntity.Direction;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyBoardPlayerController extends KeyBoardEntityController<Player> {
  public Direction direction = Direction.NONE;

  public KeyBoardPlayerController() {
    super();
  }

  public KeyBoardPlayerController(Collection<EventHandler<KeyEvent>> handlerList) {
    super(handlerList);
  }

  @Override public void control(Player player, World world) {
    player.move(direction);
  }

  @Override public void handle(KeyEvent event) {
    switch(event.getCode()) {
      case UP:
        direction = Direction.UP;
        break;
      case DOWN:
        direction = Direction.DOWN; 
        break;
      case LEFT:
        direction = Direction.LEFT; 
        break;
      case RIGHT:
        direction = Direction.RIGHT;
        break;
      case SPACE:
      
      break;
      default:
    }
  }
}
