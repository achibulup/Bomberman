package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.map.World;

import javafx.scene.canvas.GraphicsContext;

import static com.uet.int2204.group2.utils.Constants.TILE_SIZE;

public abstract class Entity {
  private World world; // the world this entity is in.
  private boolean expired = false; 

  public World getWorld() {
    return this.world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

  // tells whether the entity is expired and should be removed from worlds and lists.
  public boolean isExpired() {
    return this.expired;
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public void markExpired() {
    this.expired = true;
  }

  // this will be called after the entity is removed from the world, this method may be overriden.
  public void onRemoval() {
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

  public static boolean collides(Entity a, Entity b) {
    return a.getPixelX() + TILE_SIZE > b.getPixelX() 
        && b.getPixelX() + TILE_SIZE > a.getPixelX()
        && a.getPixelY() + TILE_SIZE > b.getPixelY() 
        && b.getPixelY() + TILE_SIZE > a.getPixelY();
  }

  /**
   * update the state of the entity.
   * @param dt : the mount of time has passed since last update, in nanoseconds
   * @param world : the world the entity is in
   */
  public abstract void update(double dt);

  public void renderTo(GraphicsContext target) {
    getSprite().drawTo(target, getPixelX(), getPixelY(), TILE_SIZE, TILE_SIZE);
  }
}
