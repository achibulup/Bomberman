package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.utils.Constants;

public abstract class MovableEntity extends Entity {
  public static enum Direction {
    NONE, UP, DOWN, LEFT, RIGHT
  };

  protected int tileX;
  protected int tileY;
  protected double pixelX;
  protected double pixelY;

  protected Direction direction = Direction.DOWN;

  // +--------+--------+
  // |       dir       |
  // |  from -->  to   |
  // |        |        |
  // +--------+--------+

  // protected int fromX;
  // protected int fromY;
  // protected int toX;
  // protected int toY;

  public MovableEntity(int tileX, int tileY) {
    this.tileX = tileX;
    this.tileY = tileY;
    this.pixelX = tileX * Constants.TILE_SIZE;
    this.pixelY = tileY * Constants.TILE_SIZE;
  }

  
  @Override public double getPixelX() {
    return this.pixelX;
  }

  @Override public double getPixelY() {
    return this.pixelY;
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

  public void move(double dx, double dy) {
    this.pixelX += dx;
    this.pixelY += dy;
    this.tileX = (int) ((this.pixelX / Constants.TILE_SIZE) + 0.5); 
    this.tileY = (int) ((this.pixelY / Constants.TILE_SIZE) + 0.5); 
  }

  public void move(Direction direction) {
    this.direction = direction;
  }

  public static double calcDx(Direction dir, double speed, long dt) {
    if (dir == Direction.LEFT) {
      return -speed * dt / 1000000000;
    }
    if (dir == Direction.RIGHT) {
      return speed * dt / 1000000000;
    }
    return 0;
  }

  public static double calcDy(Direction dir, double speed, long dt) {
    if (dir == Direction.UP) {
      return -speed * dt / 1000000000;
    }
    if (dir == Direction.DOWN) {
      return speed * dt / 1000000000;
    }
    return 0;
  }
}
