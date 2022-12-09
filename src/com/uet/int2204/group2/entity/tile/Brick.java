package com.uet.int2204.group2.entity.tile;

import com.uet.int2204.group2.entity.Entity;
import com.uet.int2204.group2.entity.movable.Enemy;
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
    if (this.hidden instanceof Tile tile) {
      getWorld().addTile(getTileX(), getTileY(), tile);
    } else if (this.hidden instanceof Enemy enemy) {
      getWorld().addEnemy(enemy);
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
