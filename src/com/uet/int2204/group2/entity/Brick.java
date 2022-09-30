package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Brick extends StaticEntity {

  public Brick(int x, int y) {
    super(x, y);
    //TODO Auto-generated constructor stub
  }

  @Override
  public Sprite getSprite() {
    // TODO Auto-generated method stub
    return ResourceManager.brick;
  }

  @Override
  public void update(long dt, World world) {
    // TODO Auto-generated method stub
  }
  
}
