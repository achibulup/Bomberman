package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Frog extends Enemy implements SimpleEnemy {
  public static final double SPEED = 80;

  private Animation animation = new Animation(ResourceManager.frog);
  private EntityController<? super Frog> controller = EntityController.doNothingController;

  public Frog(int tileX, int tileY) {
    super(tileX, tileY);
  }

  public Frog(int tileX, int tileY, EntityController<? super Frog> controller) {
    super(tileX, tileY);
    setController(controller);
  }

  public EntityController<? super Frog> getController() {
    return this.controller;
  }

  public void setController(EntityController<? super Frog> controller) {
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
    SimpleEnemy.super.getHit();
  }

  @Override 
  public Animation getAnimation() {
    return this.animation;
  }

  @Override
  public void setDyingAnimation() {
    this.animation = new Animation(ResourceManager.frogDie);
  }

  @Override
  public void control() {
    getController().control(this);
  }

  @Override
  public void update(double dt) {
    SimpleEnemy.super.update(dt);
  }
}
