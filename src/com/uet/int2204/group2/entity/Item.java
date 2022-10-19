package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.utils.ResourceManager;

import javafx.scene.canvas.GraphicsContext;

public abstract class Item extends Tile implements DestroyableTile {
  protected Animation explosionAnimation = null;

  public Item(){
  }

  public Item(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override
  public void update(double dt) {
    if (beingDestroyed()) {
      this.explosionAnimation.update(dt);
      if (this.explosionAnimation.isEnded()) {
        markExpired();
      }
    }
  }

  @Override
  public void renderTo(GraphicsContext target) {
    getSprite().drawTo(target, getPixelX(), getPixelY());
    if (beingDestroyed()) {
      this.explosionAnimation.currentSprite().drawTo(target, getPixelX(), getPixelY());
    }
  }

  @Override
  public void destroy() {
    this.explosionAnimation = new Animation(ResourceManager.itemExplosion);
  }

  public abstract void onCollect(Player player);

  protected boolean beingDestroyed() {
    return this.explosionAnimation != null;
  }
}
