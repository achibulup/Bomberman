package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.Conversions;
import com.uet.int2204.group2.utils.ResourceManager;

import javafx.scene.canvas.GraphicsContext;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.controller.EntityController;

public class Player extends MovableEntity {
  // the field MovableEntity.direction is the moving direction of the player.

  public static double START_SPEED = 330; // pixels per second.

  // private Direction faceDirection = Direction.DOWN; // should not be NONE.

  private Animation currentAnimation = new Animation(ResourceManager.playerWalkDown);
  private EntityController<? super Player> controller = EntityController.doNothingController;

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
    return START_SPEED;
  }

  @Override
  public Sprite getSprite() {
    return currentAnimation.currentSprite();
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

  @Override
  public void update(long dt, World world) {
    this.controller.control(this, world);
    if (isMovable(getDirection(), world)) {
      double moveDist = getSpeed() * Conversions.nanostoSeconds(dt);
      this.adjustedMove(moveDist);
    }
    this.currentAnimation.update(dt);
  }
  
  @Override
  public void renderTo(GraphicsContext target) {
    this.getSprite().drawTo(target, getPixelX(), getPixelY() - Constants.TILE_SIZE / 6);
  }

  public void placeBomb(World world) {
    if (world.getTile(getTileX(), getTileY()) instanceof Grass) {
      world.addTile(getTileX(), getTileY(), Bomb.class);
    }
  }
}
