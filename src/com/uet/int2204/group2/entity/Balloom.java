package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Balloom extends Enemy implements ControllableEnemy {
  public static final double SPEED = 60;

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

  @Override
  public void control(World world) {
    getController().control(this, world);
  }

  @Override
  public void update(long dt, World world) {
    ControllableEnemy.super.update(dt, world);
    this.animation.update(dt);
  }

}
