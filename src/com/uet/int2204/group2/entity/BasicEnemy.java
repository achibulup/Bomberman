package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.entity.MovableEntity.Direction;
import com.uet.int2204.group2.utils.Conversions;

// a template for writing move implementation for enemies.
public interface BasicEnemy {
  Direction getDirection();

  boolean isMovable(Direction dir, World world);

  void adjustedMove(double distance);

  boolean isAligned();

  double getSpeed();

  void control(World world);

  public default void update(long dt, World world) {
    if (isMovable(getDirection(), world)) {
      adjustedMove(getSpeed() * Conversions.nanostoSeconds(dt));
    }
    if (isAligned()) {
      control(world);
    }
  }
}
