package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;
import com.uet.int2204.group2.World;

public class Grass extends StaticEntity {
  public boolean isShadowed = false;

  public Grass(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override
  public Sprite getSprite() {
    if (isShadowed) {
      return ResourceManager.grassShadowed;
    }
    return ResourceManager.grassNormal;
  }

  @Override
  public void update(long dt, World world) {
    if (getTileY() == 1) isShadowed = false;
    else {
      StaticEntity tileAbove = world.getTile(getTileX(), getTileY() - 1);
      isShadowed = (tileAbove instanceof Brick || tileAbove instanceof Wall);
    }
  }
}
