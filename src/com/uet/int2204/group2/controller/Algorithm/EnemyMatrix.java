package com.uet.int2204.group2.controller.Algorithm;

import com.uet.int2204.group2.entity.MovableEntity;

public class EnemyMatrix implements Matrix {
  private MovableEntity enemy;

  public EnemyMatrix(MovableEntity enemy) {
    this.enemy = enemy;
  }

  @Override
  public int getWidth() {
    return this.enemy.getWorld().getMapWidth() + 2;
  }

  @Override
  public int getHeight() {
    return this.enemy.getWorld().getMapHeight() + 2;
  }

  @Override
  public boolean isBlocked(int x, int y) {
    return this.enemy.blockedBy(this.enemy.getWorld().getTile(x, y).getClass());
  }
}
