package com.uet.int2204.group2.entity;

public abstract class Item extends Tile {
  public Item(int tileX, int tileY) {
    super(tileX, tileY);
  }

  public abstract void onCollect(Player player);
}
