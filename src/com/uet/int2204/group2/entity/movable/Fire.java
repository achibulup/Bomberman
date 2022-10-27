package com.uet.int2204.group2.entity.movable;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.entity.tile.Tile;
import com.uet.int2204.group2.entity.tile.item.Item;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Fire extends Enemy implements SimpleEnemy {
  public static final double SPEED = 110;

  private Animation animation = new Animation(ResourceManager.fire);
  private EntityController<? super Fire> controller = EntityController.doNothingController;

  public Fire(int tileX, int tileY) {
    super(tileX, tileY);
  }

  public Fire(int tileX, int tileY, EntityController<? super Fire> controller) {
    super(tileX, tileY);
    setController(controller);
  }

  public EntityController<? super Fire> getController() {
    return this.controller;
  }

  public void setController(EntityController<? super Fire> controller) {
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
    if (!this.isDying() && getWorld().getPlayer() != null) {
      this.getWorld().getPlayer().increasePoint(160);
    }
    SimpleEnemy.super.getHit();
  }

  @Override
  public Runnable getInteractionToTile(Tile tile) {
    if (tile instanceof Item) {
      return () -> ((Item) tile).destroy();
    }
    return null;
  }

  @Override
  public Animation getAnimation() {
    return this.animation;
  }

  @Override
  public void setDyingAnimation() {
    this.animation = new Animation(ResourceManager.fireDie);
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
