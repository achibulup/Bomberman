package com.uet.int2204.group2.map;

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
