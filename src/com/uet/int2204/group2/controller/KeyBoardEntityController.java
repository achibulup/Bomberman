package com.uet.int2204.group2.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.entity.MovableEntity;
import com.uet.int2204.group2.entity.MovableEntity.Direction;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public abstract class KeyBoardEntityController<T extends MovableEntity> 
implements EntityController<T>, EventHandler<KeyEvent> {
  private List<Direction> directions = new ArrayList<>();

  public KeyBoardEntityController() {
  }

  // add this to a collection
  public KeyBoardEntityController(Collection<EventHandler<KeyEvent>> handlerList) {
    this();
    handlerList.add(this);
  }

  @Override
  public void control(T entity, World world) {
    if (this.directions.isEmpty()) {
      entity.setDirection(Direction.NONE);
    } else {
      entity.setDirection(this.directions.get(directions.size() - 1));
    }
  }

  @Override
  public void handle(KeyEvent event) {
    if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
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
        default:
      }
      if (dir != Direction.NONE && !this.directions.contains(dir)) {
        this.directions.add(dir);
      }
    }
    if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
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
        default:
      }
      this.directions.remove(dir);
    }
  }
}
