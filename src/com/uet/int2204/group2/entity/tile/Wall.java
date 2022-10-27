package com.uet.int2204.group2.entity.tile;

import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Wall extends Tile implements SolidTile {
  public Wall() {
  }

  public Wall(int x, int y) {
    super(x, y);
  }

  @Override
  public Sprite getSprite() {
    return ResourceManager.wall;
  }

  @Override
  public void update(double dt) {
  }
}
