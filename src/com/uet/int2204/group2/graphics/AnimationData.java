package com.uet.int2204.group2.graphics;

public class AnimationData {
  public static int ENDLESS = -1;

  private Sprite[] spriteSheet;
  private int loopCount = ENDLESS;
  private double delay = 0.2; // delay between frames in nanoseconds

  public AnimationData(Sprite[] spriteSheet) {
    setSpriteSheet(spriteSheet);
  }

  public AnimationData(Sprite[] spriteSheet, double delay) {
    this(spriteSheet);
    setDelay(delay);
  } 

  public AnimationData(Sprite[] spriteSheet, double delay, int loopCount) {
    this(spriteSheet, delay);
    setLoopCount(loopCount);
  }

  public AnimationData(Sprite sprite) {
    setSpriteSheet(new Sprite[] {sprite});
  }

  public Sprite[] getSpriteSheet() {
    return spriteSheet;
  }
  
  public void setSpriteSheet(Sprite[] spriteSheet) {
    this.spriteSheet = spriteSheet;
  }

  public double getDelay() {
    return delay;
  }

  public void setDelay(double delay) {
    this.delay = delay;
  }

  public int getLoopCount() {
    return loopCount;
  }

  public void setLoopCount(int loopCount) {
    this.loopCount = loopCount;
  }
}
