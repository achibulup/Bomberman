package com.uet.int2204.group2.graphics;

public class Animation {
  public static int ENDLESS = -1;

  private Sprite[] spriteSheet;
  private int remainingLoops = ENDLESS;
  private long delay = 200000000; // delay between frames in nanoseconds

  private long timer = 0; // timer for current frame in nanoseconds
  private int iter = 0;

  public Animation(Sprite[] spriteSheet) {
    setSpriteSheet(spriteSheet);
  }

  public Animation(Sprite[] spriteSheet, long delay) {
    this(spriteSheet);
    setDelay(delay);
  } 

  public Animation(Sprite[] spriteSheet, long delay, int loopCount) {
    this(spriteSheet, delay);
    setLoopCount(loopCount);
  }

  public Sprite[] getSpriteSheet() {
    return spriteSheet;
  }

  public int getRemainingLoops() {
    return remainingLoops;
  }

  public long getDelay() {
    return delay;
  }

  public void setSpriteSheet(Sprite[] spriteSheet) {
    this.spriteSheet = spriteSheet;
  }

  public void setLoopCount(int loopCount) {
    this.remainingLoops = loopCount;
  }

  public void setDelay(long delay) {
    this.delay = delay;
  }
  
  public Sprite currentSprite() {
    if (isEnded()) {
      return this.spriteSheet[this.spriteSheet.length - 1];
    }
    return this.spriteSheet[this.iter];
  }

  public void update(long dt) {
    if (!this.isEnded()) {
      this.timer += dt;
      if (this.timer >= delay) {
        this.timer -= delay;
        this.iter++;
        if (this.remainingLoops != 0 && this.iter == this.spriteSheet.length) {
          if (this.remainingLoops != ENDLESS) {
            --this.remainingLoops;
          }
          this.iter = 0;
        }
      }  
    }
  }

  public boolean isEnded() {
    return this.remainingLoops == 0 && this.iter == this.spriteSheet.length;
  }
}
