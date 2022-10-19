package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Edge extends Tile implements SolidTile {
  public static enum Type {
    TOP, TOP_LEFT, TOP_RIGHT, LEFT, RIGHT, BOTTOM
  }
  private Type type;

  public Edge(Type type) {
    this.type = type;
  }

  public Edge(int x, int y, Type type) {
    super(x, y);
    this.type = type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  @Override
  public Sprite getSprite() {
    switch(this.type) {
      case TOP :
        return ResourceManager.topEdge;
      case TOP_LEFT :
        return ResourceManager.topLeftEdge;
      case TOP_RIGHT :
        return ResourceManager.topRightEdge;
      case LEFT :
        return ResourceManager.leftEdge;
      case RIGHT :
        return ResourceManager.rightEdge;
      default :
        return ResourceManager.bottomEdge;
    }
  }

  @Override
  public void update(double dt) {
  }
}
