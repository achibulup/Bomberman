package com.uet.int2204.group2.graphics;

public class AnimationData {
  public static int ENDLESS = -1;

  private Sprite[] spriteSheet;
  private int loopCount = ENDLESS;
  private long delay = 200000000; // delay between frames in nanoseconds

  public AnimationData(Sprite[] spriteSheet) {
    setSpriteSheet(spriteSheet);
  }

  public AnimationData(Sprite[] spriteSheet, long delay) {
    this(spriteSheet);
    setDelay(delay);
  } 

  public AnimationData(Sprite[] spriteSheet, long delay, int loopCount) {
    this(spriteSheet, delay);
    setLoopCount(loopCount);
  }

  public AnimationData(Sprite sprite) {
    setSpriteSheet(new Sprite[] {sprite});
  }

  public AnimationData(Sprite sprite, long delay) {
    this(sprite);
    setDelay(delay);
  } 

  public AnimationData(Sprite sprite, long delay, int loopCount) {
    this(sprite, delay);
    setLoopCount(loopCount);
  }

  public Sprite[] getSpriteSheet() {
    return spriteSheet;
  }
  
  public void setSpriteSheet(Sprite[] spriteSheet) {
    this.spriteSheet = spriteSheet;
  }

  public long getDelay() {
    return delay;
  }

  public void setDelay(long delay) {
    this.delay = delay;
  }

  public int getLoopCount() {
    return loopCount;
  }

  public void setLoopCount(int loopCount) {
    this.loopCount = loopCount;
  }
}
