package com.uet.int2204.group2.entity.tile;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Portal extends Tile {
  private Animation animation = null;

  public Portal() {
  }

  public Portal(int tileX, int tileY) {
    super(tileX, tileY);
  }

  public boolean isBlinking() {
    return this.animation != null;
  }

  public void setBlinking(boolean blinking) {
    if (blinking == false) {
      this.animation = null;
    } else {
      if (this.animation == null) {
        this.animation = new Animation(ResourceManager.portalBlinking);
      }
    }
  }

  @Override
  public Sprite getSprite() {
    if (this.animation == null) return ResourceManager.portal;
    return this.animation.currentSprite();
  }

  @Override
  public void update(double dt) {
    if (this.animation != null) {
      this.animation.update(dt);
    }
  }

}
