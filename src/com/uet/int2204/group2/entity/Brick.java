package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Brick extends Tile {
  private Animation animation = new Animation(ResourceManager.brickSparky);

  public Brick(int x, int y) {
    super(x, y);
    //TODO Auto-generated constructor stub
  }

  @Override
  public Sprite getSprite() {
    if (this.animation == null) {
      return ResourceManager.brick;
    }
    return this.animation.currentSprite();
  }

  @Override
  public void update(long dt) {
    if (this.animation != null) {
      this.animation.update(dt);
    }
  }
  
}
