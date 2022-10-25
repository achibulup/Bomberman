package com.uet.int2204.group2.entity;

import static com.uet.int2204.group2.utils.Constants.TILE_SIZE;

import com.uet.int2204.group2.Sound.Sound;
import com.uet.int2204.group2.World;
import com.uet.int2204.group2.utils.Direction;
import com.uet.int2204.group2.utils.ResourceManager;

public abstract class MovableEntity extends Entity {
  protected int tileX;
  protected int tileY;
  protected double pixelX;
  protected double pixelY;

  protected Direction direction = Direction.DOWN;

  protected boolean dying = false;

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

  public boolean isDying() {
    return this.dying;
  }

  public void setDying() {
    this.dying = true;
  }

  public boolean collidesWith(Class<? extends Tile> tile) {
    return SolidTile.class.isAssignableFrom(tile);
  }

  // called when the entity get hit (eg. by flame)
  public void getHit() {
    setDying();
    markExpired();
  }

  public boolean isMovable(Direction direction) {
    if (direction == Direction.NONE) {
      return false;
    }

    World world = getWorld();
    // displacement from the main tile
    double dx = getPixelX() - getTileX() * TILE_SIZE;
    double dy = getPixelY() - getTileY() * TILE_SIZE;

    // if the entity is not aligned along the direction's axis, return true
    if (direction.dotProduct(dx, dy) != 0) {
      return true;
    }

    Tile tileAhead = world.getTile(getTileX() + direction.x, getTileY() + direction.y);
    if (collidesWith(tileAhead.getClass())) {
      return false;
    }
    double perpProj = direction.turnLeft().dotProduct(dx, dy);
    if (perpProj > 0) {
      Tile tileAheadLeft = world.getTile(getTileX() + direction.x + direction.turnLeft().x, 
                                         getTileY() + direction.y + direction.turnLeft().y);
      if (collidesWith(tileAheadLeft.getClass())) {
        return false;
      }
    } 
    if (perpProj < 0) {
      Tile tileAheadRight = world.getTile(getTileX() + direction.x + direction.turnRight().x, 
                                          getTileY() + direction.y + direction.turnRight().y);
      if (collidesWith(tileAheadRight.getClass())) {
        return false;
      }
    }
    return true;
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
    return distance * direction.x;
  }

  protected static double calcDy(Direction direction, double distance) {
    return distance * direction.y;
  }
}
