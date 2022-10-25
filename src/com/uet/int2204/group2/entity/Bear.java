package com.uet.int2204.group2.entity;

import java.util.Random;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Bear extends Enemy implements SimpleEnemy {
  public static final double SPEED = 170;
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
  public void moveTo(double pixelX, double pixelY) {
    super.moveTo(pixelX, pixelY);
    if (isAligned()) {
      if (this.streak == 0) {
        this.streak = rand.nextInt(4) + 1;
      } else {
        --this.streak;
      }
    }
  }

  @Override
  public double getSpeed() {
    return SPEED;
  }

  @Override
  public void getHit() {
    SimpleEnemy.super.getHit();
  }

  @Override
  public Animation getAnimation() {
    return this.animation;
  }

  @Override
  public void setDyingAnimation() {
    this.animation = new Animation(ResourceManager.bearDie);
  }

  @Override
  public void control() {
    getController().control(this);
  }

  @Override
  public boolean shouldControl() {
    return this.streak == 0;
  }

  @Override
  public void update(double dt) {
    SimpleEnemy.super.update(dt);
  }
}
