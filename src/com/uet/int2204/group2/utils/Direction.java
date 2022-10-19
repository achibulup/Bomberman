package com.uet.int2204.group2.utils;

public enum Direction {
  NONE(0, 0), 
  UP(0, -1), 
  DOWN(0, 1), 
  LEFT(-1, 0), 
  RIGHT(1, 0);

  public final int x;
  public final int y;

  Direction(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Direction opposite() {
    switch (this) {
      case UP:
        return DOWN;
      case DOWN:
        return UP;
      case LEFT:
        return RIGHT;
      case RIGHT:
        return LEFT;
      default:
        return NONE;
    }
  }
  public Direction turnLeft() {
    switch (this) {
      case UP:
        return LEFT;
      case DOWN:
        return RIGHT;
      case LEFT:
        return DOWN;
      case RIGHT:
        return UP;
      default:
        return NONE;
    }
  }
  public Direction turnRight() {
    switch (this) {
      case UP:
        return RIGHT;
      case DOWN:
        return LEFT;
      case LEFT:
        return UP;
      case RIGHT:
        return DOWN;
      default:
        return NONE;
    }
  }

  /**
   * Project a vector onto this direction.
   */
  public double dotProduct(double x, double y) {
    return x * this.x + y * this.y;
  }
}

