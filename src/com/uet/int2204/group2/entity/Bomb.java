package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Bomb extends Tile {
  private Animation animation = new Animation(ResourceManager.bomb);

  public Bomb(int x, int y) {
    super(x, y);
  }

  @Override
  public Sprite getSprite() {
    return this.animation.currentSprite();
  }

  @Override
  public void update(long dt) {
    this.animation.update(dt);
  }
}
