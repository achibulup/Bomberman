package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;
import com.uet.int2204.group2.World;
import com.uet.int2204.group2.controller.EntityController;

public class Player extends MovableEntity {
  // the field MovableEntity.direction is the moving direction of the player.

  private Direction faceDirection = Direction.DOWN; // should not be NONE.

  private Animation currentAnimation = new Animation(ResourceManager.playerWalkDown);
  private EntityController<? super Player> controller = EntityController.doNothingController;

  public Player(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override public Sprite getSprite() {
    return currentAnimation.currentSprite();
  }

  public void setController(EntityController<? super Player> controller) {
    this.controller = controller;
  }

  @Override public void move(Direction direction) {
    if (this.direction != direction) {
      this.direction = direction;
      switch (this.direction) {
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

  @Override public void update(long dt, World world) {
    this.controller.control(this, world);
    this.currentAnimation.update(dt);
  } 
}
