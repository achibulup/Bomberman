package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Balloom extends Enemy {
  public static final double SPEED = 70;

  private Animation animation = new Animation(ResourceManager.balloom);
  private EntityController<? super Balloom> controller = EntityController.doNothingController;

  public Balloom(int tileX, int tileY) {
    super(tileX, tileY);
  }

  public Balloom(int tileX, int tileY, EntityController<? super Balloom> controller) {
    super(tileX, tileY);
    setController(controller);
  }

  public EntityController<? super Balloom> getController() {
    return this.controller;
  }

  public void setController(EntityController<? super Balloom> controller) {
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

  public void control() {
    getController().control(this);
  }

  @Override
  public void update(double dt) {
    if (!isDying()) {
      if (isMovable(getDirection())) {
        adjustedMove(getSpeed() * dt);
      }
      if (isAligned()) {
        control();
      }
      this.animation.update(dt);
    }
  }

}
