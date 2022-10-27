package com.uet.int2204.group2.controller;

import com.uet.int2204.group2.entity.movable.MovableEntity;
import com.uet.int2204.group2.utils.Direction;

import java.util.Random;


public class AILowMoveController implements EntityController<MovableEntity> {

  public static final AILowMoveController INSTANCE = new AILowMoveController();

  private static final Random rand = new Random();


  @Override
  public void control(MovableEntity entity) {
    if (!entity.isMovable(Direction.UP)
     && !entity.isMovable(Direction.DOWN)
     && !entity.isMovable(Direction.LEFT)
     && !entity.isMovable(Direction.RIGHT)) {
      entity.setDirection(Direction.NONE);
      return;
    }
    while (true) {
      Direction tryDir = randDir();
      if (entity.isMovable(tryDir)) {
        entity.setDirection(tryDir);
        break;
      }
    }
  }

  private static Direction randDir() {
    int dirCode = rand.nextInt(4);
    switch (dirCode) {
      case 0:
        return Direction.UP;
      case 1:
        return Direction.DOWN;
      case 2:
        return Direction.LEFT;
      case 3:
        return Direction.RIGHT;
    }
    throw new RuntimeException("Random.nextInt(4) return value out of range");
  }


}
