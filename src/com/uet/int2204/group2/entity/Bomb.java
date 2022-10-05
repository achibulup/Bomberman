package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

// TODO:
public class Bomb extends Tile implements SolidTile, DestroyableTile {
  private Animation animation = new Animation(ResourceManager.bomb);
  private Player owner;
  private double timer = 1.5;

  public Bomb(int x, int y, Player owner) {
    super(x, y);
    this.owner = owner;
  }

  public Player getOwner() {
    return this.owner;
  }

  @Override
  public Sprite getSprite() {
    return this.animation.currentSprite();
  }

  @Override
  public void update(double dt) {
    this.animation.update(dt);
    this.timer -= dt;
    if (this.timer <= 0) {
      explode();
    }
  }

  @Override 
  public void onRemoval() {
    getWorld().addTile(getTileX(), getTileY(), 
                       new Flame(getTileX(), getTileY(), Flame.Type.CENTER));
    int length = getOwner().getFlameLength();

    // up direction
    for (int i = 1; i <= length; ++i) {
      int tileX = getTileX();
      int tileY = getTileY() - i;
      Tile nextTile = getOwner().getWorld().getTile(tileX, tileY);
      if (nextTile instanceof DestroyableTile) {
        ((DestroyableTile) nextTile).destroy();
        break;
      }
      if (nextTile instanceof SolidTile) {
        break;
      }
      if (i == length) {
        getWorld().addTile(tileX, tileY, new Flame(tileX, tileY, Flame.Type.UP));
      } else {
        getWorld().addTile(tileX, tileY, new Flame(tileX, tileY, Flame.Type.VERTICAL));
      }
    }
    // down direction
    for (int i = 1; i <= length; ++i) {
      int tileX = getTileX();
      int tileY = getTileY() + i;
      Tile nextTile = getOwner().getWorld().getTile(tileX, tileY);
      if (nextTile instanceof DestroyableTile) {
        ((DestroyableTile) nextTile).destroy();
      }
      if (!(nextTile instanceof Grass || nextTile instanceof Flame)) {
        break;
      }
      if (i == length) {
        getWorld().addTile(tileX, tileY, new Flame(tileX, tileY, Flame.Type.DOWN));
      } else {
        getWorld().addTile(tileX, tileY, new Flame(tileX, tileY, Flame.Type.VERTICAL));
      }
    }
    // left direction
    for (int i = 1; i <= length; ++i) {
      int tileX = getTileX() - i;
      int tileY = getTileY();
      Tile nextTile = getOwner().getWorld().getTile(tileX, tileY);
      if (nextTile instanceof DestroyableTile) {
        ((DestroyableTile) nextTile).destroy();
      }
      if (!(nextTile instanceof Grass || nextTile instanceof Flame)) {
        break;
      }
      if (i == length) {
        getWorld().addTile(tileX, tileY, new Flame(tileX, tileY, Flame.Type.LEFT));
      } else {
        getWorld().addTile(tileX, tileY, new Flame(tileX, tileY, Flame.Type.HORIZONTAL));
      }
    }
    // right direction
    for (int i = 1; i <= length; ++i) {
      int tileX = getTileX() + i;
      int tileY = getTileY();
      Tile nextTile = getOwner().getWorld().getTile(tileX, tileY);
      if (nextTile instanceof DestroyableTile) {
        ((DestroyableTile) nextTile).destroy();
      }
      if (!(nextTile instanceof Grass || nextTile instanceof Flame)) {
        break;
      }
      if (i == length) {
        getWorld().addTile(tileX, tileY, new Flame(tileX, tileY, Flame.Type.RIGHT));
      } else {
        getWorld().addTile(tileX, tileY, new Flame(tileX, tileY, Flame.Type.HORIZONTAL));
      }
    }
  }

  @Override
  public void destroy() {
    explode();
  }

  public void explode() {
    markExpired();
  }
}
