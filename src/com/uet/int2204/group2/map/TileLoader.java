package com.uet.int2204.group2.map;

import com.uet.int2204.group2.World;

public interface TileLoader {
  void load(World world, char code, int tileX, int tileY);
}
