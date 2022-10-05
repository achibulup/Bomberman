package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.ResourceManager;

// TODO:
public class Player extends MovableEntity {
  // the field MovableEntity.direction is the moving direction of the player.

  private static double NUDGE_TOLERANCE = Constants.TILE_SIZE / 2.5;
  public static double INITIAL_SPEED = 120; // pixels per second.

  // private Direction faceDirection = Direction.DOWN; // should not be NONE.

  private Animation currentAnimation = new Animation(ResourceManager.playerWalkDown);
  private EntityController<? super Player> controller = EntityController.doNothingController;
  private double speed = INITIAL_SPEED;
  private int flameLength = 1;
  private int maxBombCount = 2;

  private int remainingBombs = maxBombCount;

  public Player(int tileX, int tileY) {
    super(tileX, tileY);
  }

  public Player(int tileX, int tileY, EntityController<? super Player> controller) {
    super(tileX, tileY);
    setController(controller);
  }

  public EntityController<? super Player> getController() {
    return this.controller;
  }

  public void setController(EntityController<? super Player> controller) {
    this.controller = controller;
  }

  @Override
  public double getSpeed() {
    return this.speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public int getFlameLength() {
    return this.flameLength;
  }

  public void setFlameLength(int flameLength) {
    this.flameLength = flameLength;
  }

  public int getMaxBombCount() {
    return this.maxBombCount;
  }

  public void setMaxBombCount(int maxBombCount) {
    this.maxBombCount = maxBombCount;
    this.remainingBombs = maxBombCount;
  }

  @Override
  public Sprite getSprite() {
    return currentAnimation.currentSprite();
  };

  @Override
  public void update(double dt) {
    this.controller.control(this);
    if (getWorld().getTile(getTileX(), getTileY()) instanceof Item) {
      collect((Item) getWorld().getTile(getTileX(), getTileY()));
    }
    double moveDist = getSpeed() * dt;
    if (isMovable(getDirection())) {
      adjustedMove(moveDist);
    } else {
      cornerCorrection(moveDist);
    }
    this.currentAnimation.update(dt);
  }

  @Override
  public void setDirection(Direction direction) {
    if (this.direction != direction) {
      if (direction == Direction.NONE) {
        switch (this.direction) {
          case UP:
            this.currentAnimation = new Animation(ResourceManager.playerIdleUp);
            break;
          case DOWN:
            this.currentAnimation = new Animation(ResourceManager.playerIdleDown);
            break;
          case LEFT: 
            this.currentAnimation = new Animation(ResourceManager.playerIdleLeft);
            break;
          case RIGHT:
            this.currentAnimation = new Animation(ResourceManager.playerIdleRight);
            break;
          default:
        }
      } else {
        switch (direction) {
          case UP:
            this.currentAnimation = new Animation(ResourceManager.playerWalkUp);
            break;
          case DOWN:
            this.currentAnimation = new Animation(ResourceManager.playerWalkDown);
            break;
          case LEFT: 
            this.currentAnimation = new Animation(ResourceManager.playerWalkLeft);
            break;
          case RIGHT:
            this.currentAnimation = new Animation(ResourceManager.playerWalkRight);
            break;
          default:
        }
      }
    }
    this.direction = direction;
  }

  public void placeBomb() {
    // TODO:
    if (this.remainingBombs == 0 
     || getWorld().getTile(getTileX(), getTileY()) instanceof SolidTile) {
      return;
    }
    this.remainingBombs--;
    Bomb bomb = new Bomb(getTileX(), getTileY(), this);
    getWorld().addTile(getTileX(), getTileY(), bomb);
  }

  public void collect(Item item) {
    item.onCollect(this);
    item.markExpired();
  }

  /**
   * makes the player nudge if the player hit a corner.
   * the maximum nudge distance is equal to {@code moveDist}
   * @param moveDist : the distance moved if the player doesn't hit the wall 
   */
  public void cornerCorrection(double moveDist) {
    double alignX = getTileX() * Constants.TILE_SIZE;
    double alignY = getTileY() * Constants.TILE_SIZE;
    switch (getDirection()) {
      case UP:
        if (!collidesWith(getWorld().getTile(getTileX(), getTileY() - 1).getClass())) {
          if (getPixelX() < alignX && alignX - getPixelX() <= NUDGE_TOLERANCE) {
            adjustedMove(Direction.RIGHT, moveDist);
            break;
          }
          if (getPixelX() > alignX && getPixelX() - alignX <= NUDGE_TOLERANCE) {
            adjustedMove(Direction.LEFT, moveDist);
            break;
          }
        }
        break;
      case DOWN:
        if (!collidesWith(getWorld().getTile(getTileX(), getTileY() + 1).getClass())) {
          if (getPixelX() < alignX && alignX - getPixelX() <= NUDGE_TOLERANCE) {
            adjustedMove(Direction.RIGHT, moveDist);
            break;
          }
          if (getPixelX() > alignX && getPixelX() - alignX <= NUDGE_TOLERANCE) {
            adjustedMove(Direction.LEFT, moveDist);
            break;
          }
        }
        break;
      case LEFT:
        if (!collidesWith(getWorld().getTile(getTileX() - 1, getTileY()).getClass())) {
          if (getPixelY() < alignY && alignY - getPixelY() <= NUDGE_TOLERANCE) {
            adjustedMove(Direction.DOWN, moveDist);
            break;
          }
          if (getPixelY() > alignY && getPixelY() - alignY <= NUDGE_TOLERANCE) {
            adjustedMove(Direction.UP, moveDist);
            break;
          }
        }
        break;
      case RIGHT:
        if (!collidesWith(getWorld().getTile(getTileX() + 1, getTileY()).getClass())) {
          if (getPixelY() < alignY && alignY - getPixelY() <= NUDGE_TOLERANCE) {
            adjustedMove(Direction.DOWN, moveDist);
            break;
          }
          if (getPixelY() > alignY && getPixelY() - alignY <= NUDGE_TOLERANCE) {
            adjustedMove(Direction.UP, moveDist);
            break;
          }
        }
        break;
      default:
    }
  }
}
