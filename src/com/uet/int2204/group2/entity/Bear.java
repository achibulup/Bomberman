package com.uet.int2204.group2.entity;

import java.util.Random;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Bear extends Enemy {
  public static final double SPEED = 70;
  public static final int MAX_STREAK = 4;

  private static final Random rand = new Random();

  private Animation animation = new Animation(ResourceManager.bear);
  private EntityController<? super Bear> controller = EntityController.doNothingController;
  private int streak = 0;

  public Bear(int tileX, int tileY) {
    super(tileX, tileY);
  }

  public Bear(int tileX, int tileY, EntityController<? super Bear> controller) {
    super(tileX, tileY);
    setController(controller);
  }

  public EntityController<? super Bear> getController() {
    return this.controller;
  }

  public void setController(EntityController<? super Bear> controller) {
    this.controller = controller;
  }

  @Override
  public Sprite getSprite() {
    return this.animation.currentSprite();
  }

  @Override
  public double getSpeed() {
    return SPEED;
  }

  @Override
  public void getHit() {
    if (isDying()) {
      return;
    }
    this.setDying();
    this.animation = new Animation(ResourceManager.bearDie);
  }

  public void control() {
    getController().control(this);
  }

  @Override
  public void update(double dt) {
    if (!isDying()) {
      if (isMovable(getDirection())) {
        adjustedMove(getSpeed() * dt);
      } else {
        this.streak = 0;
      }
      if (isAligned()) {
        if (this.streak > 0) {
          this.streak--;
        }
        if (this.streak == 0) {
          this.streak = rand.nextInt(MAX_STREAK);
          control();
        }
      }
      this.animation.update(dt);
    } else {
      this.animation.update(dt);
      if (this.animation.isEnded()) {
        markExpired();
      }
    }
  }

}
