package com.uet.int2204.group2.map;

import com.uet.int2204.group2.entity.tile.Brick;
import com.uet.int2204.group2.entity.tile.Tile;
import com.uet.int2204.group2.entity.tile.item.Item;

public class BlinkBrickTrigger implements SingleUseWorldTrigger {
  @Override
  public boolean checkCondition(World target) {
    return target.getEnemies().size() == 0;
  }

  @Override
  public void activate(World target) {
    int width = target.getMapWidth();
    int height = target.getMapHeight();
    for (int i = 1; i <= width; ++i) {
      for (int j = 1; j <= height; ++j) {
        Tile tile = target.getTile(i, j);
        if (tile instanceof Brick brick) {
          if (brick.getHiddenEntity() instanceof Item) {
            brick.setSparky(true);
          }
        }
      }
    }
  }
}
