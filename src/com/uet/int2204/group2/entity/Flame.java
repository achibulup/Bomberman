package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Flame extends Tile {
  public enum Type {
    UP, DOWN, LEFT, RIGHT, HORIZONTAL, VERTICAL, CENTER
  };

  private Animation animation;

  public Flame(Type type) {
    setType(type);
  }

  public Flame(int x, int y, Type type) {
    super(x, y);
    setType(type);
  }

  public void setType(Type type) {
    switch (type) {
      case UP:
        this.animation = new Animation(ResourceManager.upFlame);
        break;
      case DOWN:
        this.animation = new Animation(ResourceManager.downFlame);
        break;
      case LEFT:
        this.animation = new Animation(ResourceManager.leftFlame);
        break;
      case RIGHT:
        this.animation = new Animation(ResourceManager.rightFlame);
        break;
      case HORIZONTAL:
        this.animation = new Animation(ResourceManager.horizontalFlame);
        break;
      case VERTICAL:
        this.animation = new Animation(ResourceManager.verticalFlame);
        break;
      case CENTER:
        this.animation = new Animation(ResourceManager.centerFlame);
        break;
      default:
    }
  }

  @Override
  public Sprite getSprite() {
    return this.animation.currentSprite();
  }

  @Override
  public void update(double dt) {
    this.animation.update(dt);
    if (this.animation.isEnded()) {
      markExpired();
    }
  }
}
