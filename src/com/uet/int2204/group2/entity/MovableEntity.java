package com.uet.int2204.group2.entity;

import static com.uet.int2204.group2.utils.Constants.TILE_SIZE;

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
  // |       direction       |
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
    this.pixelX = tileX * TILE_SIZE;
    this.pixelY = tileY * TILE_SIZE;
  }

  
  @Override
  public double getPixelX() {
    return this.pixelX;
  }

  @Override
  public double getPixelY() {
    return this.pixelY;
  }

  @Override
  public int getTileX() {
    return this.tileX;
  }

  @Override
  public int getTileY() {
    return this.tileY;
  }

  public boolean isMovable(Direction direction) {
    return true;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void move(double dx, double dy) {
    moveTo(pixelX + dx, pixelY + dy);
  }

  public void move(double distance) {
    move(calcDx(this.direction, distance), calcDy(this.direction, distance));
  }

  // snaps into a tile when appropriate
  public void correctedMove(double distance) {
    if (this.direction == Direction.NONE) {
      return;
    }
    double newX = this.pixelX + calcDx(this.direction, distance);
    if (this.pixelX < this.tileX * TILE_SIZE && newX > this.tileX * TILE_SIZE) {
      newX = this.tileX * TILE_SIZE;
    }
    if (this.pixelX > this.tileX * TILE_SIZE && newX < this.tileX * TILE_SIZE) {
      newX = this.tileX * TILE_SIZE;
    }
    double newY = this.pixelY + calcDy(this.direction, distance);
    if (this.pixelY < this.tileY * TILE_SIZE && newY > this.tileY * TILE_SIZE) {
      newY = this.tileY * TILE_SIZE;
    }
    if (this.pixelY > this.tileY * TILE_SIZE && newY < this.tileY * TILE_SIZE) {
      newY = this.tileY * TILE_SIZE;
    }
    moveTo(newX, newY);
  }

  public void moveTo(double pixelX, double pixelY) {
    this.pixelX = pixelX;
    this.pixelY = pixelY;
    this.tileX = (int) ((this.pixelX / TILE_SIZE) + 0.5); 
    this.tileY = (int) ((this.pixelY / TILE_SIZE) + 0.5); 
  }

  // checks if this entity is perfectly aligned with a tile
  public boolean isAligned() {
    return getPixelX() == getTileX() * TILE_SIZE
        && getPixelY() == getTileY() * TILE_SIZE;
  }

  public static double calcDx(Direction direction, double distance) {
    if (direction == Direction.LEFT) {
      return -distance;
    }
    if (direction == Direction.RIGHT) {
      return distance;
    }
    return 0;
  }

  public static double calcDy(Direction direction, double distance) {
    if (direction == Direction.UP) {
      return -distance;
    }
    if (direction == Direction.DOWN) {
      return distance;
    }
    return 0;
  }
}
