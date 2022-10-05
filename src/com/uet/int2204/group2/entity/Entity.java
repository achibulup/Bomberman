package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.World;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
  protected World world; // the world this entity is in.

  public World getWorld() {
    return this.world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

  // get the x position of the main tile the entity is in.
  public abstract int getTileX();
  
  // get the y position of the main tile the entity is in.
  public abstract int getTileY();

  // get the x position in pixels.
  public abstract double getPixelX();

  // get the y position in pixels.
  public abstract double getPixelY();
  
  // get the current sprite of the entity.
  public abstract Sprite getSprite();

  /**
   * update the state of the entity.
   * @param dt : the mount of time has passed since last update, in nanoseconds
   * @param world : the world the entity is in
   */
  public abstract void update(long dt);

  public void renderTo(GraphicsContext target) {
    getSprite().drawTo(target, getPixelX(), getPixelY());
  }
}
