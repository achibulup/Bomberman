package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Grass extends Tile {
  public Grass(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override
  public Sprite getSprite() {
    boolean isShadowed;
    if (getTileY() == 1) isShadowed = false;
    else {
      Tile tileAbove = getWorld().getTile(getTileX(), getTileY() - 1);
      isShadowed = (tileAbove instanceof Brick || tileAbove instanceof Wall);
    }
    if (isShadowed) {
      return ResourceManager.grassShadowed;
    }
    return ResourceManager.grassNormal;
  }

  @Override
  public void update(double dt) {
  }
}
