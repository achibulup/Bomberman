package com.uet.int2204.group2.entity.movable;

public abstract class Enemy extends MovableEntity {
  public Enemy(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override
  public Runnable getInteractionToEntity(MovableEntity entity) {
    if (entity instanceof Player) {
      return () -> entity.getHit();
    }
    return null;
  }
}
