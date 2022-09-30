package com.uet.int2204.group2.graphics;

import static com.uet.int2204.group2.graphics.AnimationData.ENDLESS;

public class Animation {
  private AnimationData data;

  private long timer = 0; // timer for current frame in nanoseconds
  private int iter = 0;
  private int remainingLoops;

  public Animation(AnimationData data) {
    setData(data);
    setLoopCount(data.getLoopCount());
  }

  public AnimationData getData() {
    return this.data;
  }

  public void setData(AnimationData data) {
    this.data = data;
  }

  public int getRemainingLoops() {
    return remainingLoops;
  }

  public void setLoopCount(int loopCount) {
    this.remainingLoops = loopCount;
  }
  
  public Sprite currentSprite() {
    if (isEnded()) {
      return getSpriteSheet()[getSpriteSheet().length - 1];
    }
    return getSpriteSheet()[this.iter];
  }

  public void update(long dt) {
    if (!this.isEnded()) {
      this.timer += dt;
      while (this.timer >= getAnimationDelay()) {
        this.timer -= getAnimationDelay();
        this.iter++;
        if (this.remainingLoops != 0 && this.iter == getSpriteSheet().length) {
          if (this.remainingLoops != ENDLESS) {
            --this.remainingLoops;
          }
          this.iter = 0;
        }
      }  
    }
  }

  public boolean isEnded() {
    return this.remainingLoops == 0 && this.iter == getSpriteSheet().length;
  }

  private Sprite[] getSpriteSheet() {
    return getData().getSpriteSheet();
  }

  private long getAnimationDelay() {
    return getData().getDelay();
  }
}
