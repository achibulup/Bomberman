package com.uet.int2204.group2.map;

import com.uet.int2204.group2.World;

public abstract class WorldExtension {
  private World world;
  
  public WorldExtension() {
  }

  public WorldExtension(World world) {
    setWorld(world);
  }
  
  public World getWorld() {
    return this.world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

  public abstract void update(double dt);
}
