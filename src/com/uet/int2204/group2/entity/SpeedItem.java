package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class SpeedItem extends Item {
  private Animation animation = new Animation(ResourceManager.speedItem);

  public SpeedItem(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override
  public Sprite getSprite() {
    return this.animation.currentSprite();
  }

  @Override
  public void update(long dt) {
    this.animation.update(dt);
  }
  
  @Override
  public void onCollect(Player player) {
    player.setSpeed(player.getSpeed() + 90);
  }
}
