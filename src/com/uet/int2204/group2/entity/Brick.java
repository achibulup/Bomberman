package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Brick extends Tile implements SolidTile, DestroyableTile {
  private Animation animation = null;
  private boolean destroying = false;
  private Entity hidden = null;

  public Brick() {
  }

  public Brick(Entity hidden) {
    setHiddenEntity(hidden);
  }

  public Brick(int x, int y) {
    super(x, y);
  }

  public Brick(int x, int y, Entity hidden) {
    super(x, y);
    setHiddenEntity(hidden);
  }

  @Override
  public Sprite getSprite() {
    if (this.animation == null) {
      return ResourceManager.brick;
    }
    return this.animation.currentSprite();
  }

  @Override
  public void update(double dt) {
    if (this.animation != null) {
      this.animation.update(dt);
      if (this.animation.isEnded()) {
        markExpired();
      }
    }
  }

  @Override
  public void destroy() {
    if (!this.destroying) {
      this.destroying = true;
      this.animation = new Animation(ResourceManager.brickExplosion);
    }
  }

  @Override
  public void onRemoval() {
    if (this.hidden instanceof Tile) {
      getWorld().addTile(getTileX(), getTileY(), (Tile) getHiddenEntity());
    } else if (this.hidden instanceof Enemy) {
      getWorld().addEnemy((Enemy) getHiddenEntity());
    }
  }

  public Entity getHiddenEntity() {
    return this.hidden;
  }

  public void setHiddenEntity(Entity hidden) {
    this.hidden = hidden;
  }
  
  public boolean isSparky() {
    return this.animation != null;
  }

  public void setSparky(boolean sparky) {
    if (!sparky) {
      this.animation = null;
    } else {
      if (this.animation == null) {
        this.animation = new Animation(ResourceManager.brickSparky);
      }
    }
  }
}
