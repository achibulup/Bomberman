package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;
import com.uet.int2204.group2.World;

public class Player extends MovableEntity {
  private boolean isMoving;
  private Animation currentAnimation = new Animation(ResourceManager.playerWalkDown);

  public Player(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override public Sprite getSprite() {
    return currentAnimation.currentSprite();
    // switch (this.direction) {
    //   case UP :
    //     return ResourceManager.playerIdleUp;
    //   case DOWN :
    //     return ResourceManager.playerIdleDown;
    //   case LEFT : 
    //     return ResourceManager.playerIdleLeft;
    //   case RIGHT :
    //     return ResourceManager.playerIdleRight;
    //   default :
    //     return null;
    // }
  }

  @Override public void update(long dt, World world) {
    currentAnimation.update(dt);
  } 
}
