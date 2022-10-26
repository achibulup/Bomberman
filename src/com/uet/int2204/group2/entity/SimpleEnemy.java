package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.utils.Direction;

// default implementation of most enemies, classes implementing this should inherit from MovableEntity
public interface SimpleEnemy extends SimpleSpriteEnemy, ControllableEnemy {
  double getSpeed();

  Direction getDirection();

  void setDirection(Direction direction);

  boolean isAligned();

  boolean isMovable(Direction direction);

  boolean isHalfwayBlocked(Direction direction);

  boolean isDying();

  void setDying(boolean dying);

  void adjustedMove(double distance);

  void markExpired();

  default boolean shouldControl() {
    return isAligned();
  }

  default void getHit() {
    if (isDying()) {
      return;
    }
    this.setDying(true);
    setDyingAnimation();
  }

  default void update(double dt) {
    if (!isDying()) {
      if (isHalfwayBlocked(getDirection())) {
        setDirection(getDirection().opposite());
      }
      if (isMovable(getDirection())) {
        adjustedMove(getSpeed() * dt);
      }
      if (shouldControl()) {
        control();
      }
      getAnimation().update(dt);
    } else {
      getAnimation().update(dt);
      if (getAnimation().isEnded()) {
        markExpired();
      }
    }
  }
}
