package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;

public interface SimpleSpriteEnemy {
  Animation getAnimation();

  void setDyingAnimation();

  default Sprite getSprite() {
    return getAnimation().currentSprite();
  }
}
