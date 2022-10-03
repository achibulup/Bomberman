package com.uet.int2204.group2.controller;

import java.util.Random;

import com.uet.int2204.group2.entity.MovableEntity;
import com.uet.int2204.group2.entity.MovableEntity.Direction;

public class RandomMoveController implements EntityController<MovableEntity> {
  public static final RandomMoveController INSTANCE = new RandomMoveController();

  private static final Random rand = new Random();

  @Override
  public void control(MovableEntity entity) {
    entity.setDirection(randDir());
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
