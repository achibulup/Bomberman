package com.uet.int2204.group2.entity;

import java.util.ArrayList;
import java.util.List;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.Direction;
import com.uet.int2204.group2.utils.ResourceManager;

public class Player extends MovableEntity {
  // the field MovableEntity.direction is the moving direction of the player.

  private static double NUDGE_TOLERANCE = Constants.TILE_SIZE / 2.2;
  public static double INITIAL_SPEED = 150; // pixels per second.

  // private Direction faceDirection = Direction.DOWN; // should not be NONE.

  private Animation animation = new Animation(ResourceManager.playerIdleDown);
  private EntityController<? super Player> controller = EntityController.doNothingController;
  private double speed = INITIAL_SPEED;
  private int flameLength = 1;
  private int maxBombCount = 1;
  private List<Bomb> bombList = new ArrayList<>();
  private boolean enteringPortal = false;
  private int lives = 1;
  private boolean detonable = false;
  private int flameStrength = 0;

  public Player(int tileX, int tileY) {
    super(tileX, tileY);
    setDirection(Direction.DOWN);
    setDirection(Direction.NONE);
  }

  public Player(int tileX, int tileY, EntityController<? super Player> controller) {
    this(tileX, tileY);
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
  }

  public int getLives() {
    return this.lives;
  }

  public void setLives(int lives) {
    this.lives = lives;
  }

  public boolean isDetonable() {
    return this.detonable;
  }

  public void setDetonable(boolean detonable) {
    this.detonable = detonable;
  }

  public int getFlameStrength() {
    return this.flameStrength;
  }

  public void setFlameStrength(int flameStrength) {
    this.flameStrength = flameStrength;
  }

  public boolean isEnteringPortal() {
    return this.enteringPortal;
  }

  public void setEnteringPortal() {
    this.enteringPortal = true;
    this.direction = Direction.NONE;
    this.animation = new Animation(ResourceManager.playerEnterPortal);
  }

  @Override
  public Sprite getSprite() {
    return animation.currentSprite();
  };

  @Override
  public void getHit() {
    if (isDying()) {
      return;
    }
    setDying(true);
    setDetonable(false);
    this.animation = new Animation(ResourceManager.playerDead);
  }

  @Override
  public void onRemoval() {
    getWorld().setGameOver(true);
  }

  @Override
  public Runnable getInteractionToTile(Tile tile) {
    if (tile instanceof Item) {
      return () -> this.collect((Item) tile);
    }
    return null;
  }

  @Override
  public void update(double dt) {
    if (isEnteringPortal()) {
      this.animation.update(dt);
      if (this.animation.isEnded()) {
        this.markExpired();
      }
    } else if (!isDying()) {
      this.controller.control(this);
      this.animation.update(dt);
      double moveDist = getSpeed() * dt;
      if (isMovable(getDirection())) {
        adjustedMove(moveDist);
      } else {
        cornerCorrection(moveDist);
      }
    } else {
      this.animation.update(dt);
      if (this.animation.isEnded()) {
        this.markExpired();
      }
    }
  }

  @Override
  public void setDirection(Direction direction) {
    if (this.direction != direction) {
      if (direction == Direction.NONE) {
        switch (this.direction) {
          case UP:
            this.animation = new Animation(ResourceManager.playerIdleUp);
            break;
          case DOWN:
            this.animation = new Animation(ResourceManager.playerIdleDown);
            break;
          case LEFT: 
            this.animation = new Animation(ResourceManager.playerIdleLeft);
            break;
          case RIGHT:
            this.animation = new Animation(ResourceManager.playerIdleRight);
            break;
          default:
        }
      } else {
        switch (direction) {
          case UP:
            this.animation = new Animation(ResourceManager.playerWalkUp);
            break;
          case DOWN:
            this.animation = new Animation(ResourceManager.playerWalkDown);
            break;
          case LEFT: 
            this.animation = new Animation(ResourceManager.playerWalkLeft);
            break;
          case RIGHT:
            this.animation = new Animation(ResourceManager.playerWalkRight);
            break;
          default:
        }
      }
    }
    this.direction = direction;
  }

  public void placeBomb() {
    updateBombList();
    if (this.bombList.size() == this.maxBombCount
     || getWorld().getTile(getTileX(), getTileY()) instanceof SolidTile) {
      return;
    }
    Bomb newBomb = new Bomb(getTileX(), getTileY(), this);
    this.bombList.add(newBomb);
    getWorld().addTile(getTileX(), getTileY(), newBomb);
  }

  public void collect(Item item) {
    if (!item.beingDestroyed()) {
      item.onCollect(this);
      item.markExpired();
    }
  }

  public void detonateBomb() {
    if (!isDetonable()) {
      return;
    }
    for (Bomb bomb : this.bombList) {
      if (!bomb.isExpired()) {
        bomb.explode();
        break;
      }
    }
  }

  /**
   * makes the player nudge if the player hit a corner.
   * the maximum nudge distance is equal to {@code moveDist}
   * @param moveDist : the distance moved if the player doesn't hit the wall 
   */
  public void cornerCorrection(double moveDist) {
    double alignX = getTileX() * Constants.TILE_SIZE;
    double alignY = getTileY() * Constants.TILE_SIZE;
    Direction currentDir = getDirection();
    Tile tileAhead = getWorld().getTile(getTileX() + currentDir.x, getTileY() + currentDir.y);

    if (!blockedBy(tileAhead.getClass())) {
      double proj = currentDir.turnLeft().dotProduct(getPixelX() - alignX, getPixelY() - alignY);
      if (0 < proj && proj <= NUDGE_TOLERANCE) {
        adjustedMove(currentDir.turnRight(), moveDist);
      } else if (-NUDGE_TOLERANCE <= proj && proj < 0) {
        adjustedMove(currentDir.turnLeft(), moveDist);
      }
    }
  }

  public void updateBombList() {
    this.bombList.removeIf((bomb) -> bomb.isExpired());
  }
}
