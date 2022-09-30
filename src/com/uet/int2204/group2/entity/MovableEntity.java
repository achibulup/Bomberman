package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.utils.Constants;

public abstract class MovableEntity extends Entity {
  public static enum Direction {
    NONE, UP, DOWN, LEFT, RIGHT
  };

  protected int tileX;
  protected int tileY;
  protected int pixelX;
  protected int pixelY;

  protected Direction direction = Direction.DOWN;

  // +--------+--------+
  // |       dir       |
  // |  from -->  to   |
  // |        |        |
  // +--------+--------+

  protected int fromX;
  protected int fromY;
  protected int toX;
  protected int toY;

  public MovableEntity(int tileX, int tileY) {
    this.tileX = tileX;
    this.tileY = tileY;
    this.pixelX = tileX * Constants.TILE_SIZE;
    this.pixelY = tileY * Constants.TILE_SIZE;
  }

  
  @Override public int getPixelX() {
    return pixelX;
  }

  @Override public int getPixelY() {
    return pixelY;
  }

  @Override public int getTileX() {
    return this.tileX;
  }

  @Override public int getTileY() {
    return this.tileY;
  }

  public boolean isMovable(Direction dir) {
    return true;
  }

  public void move(Direction direction) {
    this.direction = direction;
  }
}
