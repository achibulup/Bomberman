package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;
import com.uet.int2204.group2.World;

public class Grass extends StaticEntity {
  public boolean isShadowed = false;

  public Grass(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override public Sprite getSprite() {
    // System.out.println("Grass");
    if (isShadowed) {
      return ResourceManager.grassShadowed;
    }
    return ResourceManager.grassNormal;
  }

  @Override public void update(long dt, World world) {
    if (getTileY() == 0) isShadowed = false;
    else {
      StaticEntity tileAbove = world.getTile(getTileX(), getTileY() - 1);
      isShadowed = !(tileAbove instanceof Grass || tileAbove instanceof Item);
    }
  }
}
