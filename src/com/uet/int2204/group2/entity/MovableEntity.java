package com.uet.int2204.group2.entity;

import static com.uet.int2204.group2.utils.Constants.TILE_SIZE;

import com.uet.int2204.group2.World;
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
    this.pixelX = tileX * TILE_SIZE;
    this.pixelY = tileY * TILE_SIZE;
  }

  public abstract double getSpeed();
  
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

  public Direction getDirection() {
    return this.direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public boolean collidesWith(Class<? extends Tile> tile) {
    return SolidTile.class.isAssignableFrom(tile);
  }

  public boolean isMovable(Direction direction) {
    World world = getWorld();
    switch (direction) {
      case UP:
        if (!isYAligned()) {
          return true;
        }
        if (collidesWith(world.getTile(getTileX(), getTileY() - 1).getClass())) {
          return false;
        }
        if (getPixelX() < getTileX() * Constants.TILE_SIZE
         && collidesWith(world.getTile(getTileX() - 1, getTileY() - 1).getClass())) {
          return false;
        }
        if (getPixelX() > getTileX() * Constants.TILE_SIZE
         && collidesWith(world.getTile(getTileX() + 1, getTileY() - 1).getClass())) {
          return false;
        }
        return true;
      case DOWN:
        if (!isYAligned()) {
          return true;
        }
        if (collidesWith(world.getTile(getTileX(), getTileY() + 1).getClass())) {
          return false;
        }
        if (getPixelX() < getTileX() * Constants.TILE_SIZE
         && collidesWith(world.getTile(getTileX() - 1, getTileY() + 1).getClass())) {
          return false;
        }
        if (getPixelX() > getTileX() * Constants.TILE_SIZE
         && collidesWith(world.getTile(getTileX() + 1, getTileY() + 1).getClass())) {
          return false;
        }
        return true;
      case LEFT:
        if (!isXAligned()) {
          return true;
        }
        if (collidesWith(world.getTile(getTileX() - 1, getTileY()).getClass())) {
          return false;
        }
        if (getPixelY() < getTileY() * Constants.TILE_SIZE
        && collidesWith(world.getTile(getTileX() - 1, getTileY() - 1).getClass())) {
          return false;
        }
        if (getPixelY() > getTileY() * Constants.TILE_SIZE
        && collidesWith(world.getTile(getTileX() - 1, getTileY() + 1).getClass())) {
          return false;
        }
        return true;
      case RIGHT:
        if (!isXAligned()) {
          return true;
        }
        if (collidesWith(world.getTile(getTileX() + 1, getTileY()).getClass())) {
          return false;
        }
        if (getPixelY() < getTileY() * Constants.TILE_SIZE
        && collidesWith(world.getTile(getTileX() + 1, getTileY() - 1).getClass())) {
          return false;
        }
        if (getPixelY() > getTileY() * Constants.TILE_SIZE
        && collidesWith(world.getTile(getTileX() + 1, getTileY() + 1).getClass())) {
          return false;
        }
        return true;
      default:
    }
    return false;
  }

  // checks if this entity is perfectly aligned with a tile.
  public boolean isAligned() {
    return isXAligned() && isYAligned();
  }

  // checks if this entity is perfectly aligned with a tile in the x axis.
  public boolean isXAligned() {
    return getPixelX() == getTileX() * TILE_SIZE;
  }

  // checks if this entity is perfectly aligned with a tile in the y axis.
  public boolean isYAligned() {
    return getPixelY() == getTileY() * TILE_SIZE;
  }

  public void move(double dx, double dy) {
    moveTo(getPixelX() + dx, getPixelY() + dy);
  }

  // move in this direction with a distance 
  // shouldn't be used because it makes other mechanics more complex, see {@code adjustedMove} below.
  public void move(double distance) {
    move(getDirection(), distance);
  }
  
  // move in a direction with a distance 
  // shouldn't be used because it makes other mechanics more complex, see {@code adjustedMove} below.
  public void move(Direction dir, double distance) {
    move(calcDx(dir, distance), calcDy(dir, distance));
  }

  // like {@code move} but will snap into an alignment when possible.
  // when adjustment happens actual distance traveled might be shorter.
  public void adjustedMove(double distance) {
    adjustedMove(getDirection(), distance);
  }

  // like {@code move} but will snap into an alignment when possible.
  // when adjustment happens actual distance traveled might be shorter.
  public void adjustedMove(Direction dir, double distance) {
    if (dir == Direction.NONE) {
      return;
    }
    double newX = getPixelX() + calcDx(dir, distance);
    double alignX = getTileX() * TILE_SIZE;
    if (getPixelX() < alignX && newX > alignX) {
      newX = alignX;
    }
    if (getPixelX() > alignX && newX < alignX) {
      newX = alignX;
    }
    double newY = getPixelY() + calcDy(dir, distance);
    double alignY = getTileY() * TILE_SIZE;
    if (getPixelY() < alignY && newY > alignY) {
      newY = alignY;
    }
    if (getPixelY() > alignY && newY < alignY) {
      newY = alignY;
    }
    moveTo(newX, newY);
  }

  public void moveTo(double pixelX, double pixelY) {
    this.pixelX = pixelX;
    this.pixelY = pixelY;
    this.tileX = (int) Math.floor((this.pixelX / TILE_SIZE) + 0.5); 
    this.tileY = (int) Math.floor((this.pixelY / TILE_SIZE) + 0.5); 
  }

  protected static double calcDx(Direction direction, double distance) {
    if (direction == Direction.LEFT) {
      return -distance;
    }
    if (direction == Direction.RIGHT) {
      return distance;
    }
    return 0;
  }

  protected static double calcDy(Direction direction, double distance) {
    if (direction == Direction.UP) {
      return -distance;
    }
    if (direction == Direction.DOWN) {
      return distance;
    }
    return 0;
  }
}
