package com.uet.int2204.group2.entity.tile.item;

import com.uet.int2204.group2.entity.movable.Player;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class SpeedItem extends Item {
  private Animation animation = new Animation(ResourceManager.speedItem);

  public SpeedItem() {
  }

  public SpeedItem(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override
  public Sprite getSprite() {
    return this.animation.currentSprite();
  }

  @Override
  public void update(double dt) {
    super.update(dt);
    if (!beingDestroyed()) {
      this.animation.update(dt);
    }
  }
  
  @Override
  public void onCollect(Player player) {
    player.setSpeed(player.getSpeed() + 90);
  }
}
