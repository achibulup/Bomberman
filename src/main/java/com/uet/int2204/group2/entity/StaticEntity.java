package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.utils.Constants;

public abstract class StaticEntity extends Entity {
  private int tileX;
  private int tileY;

  public StaticEntity(int x, int y) {
    this.tileX = x;
    this.tileY = y;
  }

  @Override public int getPixelX() {
    return getTileX() * Constants.TILE_SIZE;
  }

  @Override public int getPixelY() {
    return getTileY() * Constants.TILE_SIZE;
  }

  @Override public int getTileX() {
    return this.tileX;
  }

  @Override public int getTileY() {
    return this.tileY;
  }
}
