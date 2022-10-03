package com.uet.int2204.group2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.uet.int2204.group2.entity.Edge;
import com.uet.int2204.group2.entity.Enemy;
import com.uet.int2204.group2.entity.Grass;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.entity.Tile;

import javafx.scene.canvas.GraphicsContext;

public class World {
  private static class TileStack extends Stack<Tile> {
  }

  private int mapWidth;
  private int mapHeight;
  private Player player;
  private TileStack[][] map;
  private List<Enemy> enemies;

  public World(int mapWidth, int mapHeight) {
    this.mapWidth = mapWidth;
    this.mapHeight = mapHeight;

    this.map = new TileStack[mapWidth + 2][mapHeight + 2];
    for (int i = 0; i < mapWidth + 2; ++i) {
      for (int j = 0; j < mapHeight + 2; ++j) {
        map[i][j] = new TileStack();
      }
    }
    for (int i = 0; i < mapWidth + 2; ++i) {
      addTile(i, 0, new Edge(i, 0, Edge.Type.TOP));
      addTile(i, mapHeight + 1, new Edge(i, mapHeight + 1, Edge.Type.BOTTOM));
    }
    addTile(0, 0, new Edge(0, 0, Edge.Type.TOP_LEFT));
    addTile(mapWidth + 1, 0, new Edge(mapWidth + 1, 0, Edge.Type.TOP_RIGHT));
    for (int i = 1; i <= mapHeight; ++i) {
      addTile(0, i, new Edge(0, i, Edge.Type.LEFT));
      addTile(mapWidth + 1, i, new Edge(mapWidth + 1, i, Edge.Type.RIGHT));
    }
    for (int i = 1; i <= mapWidth; ++i) {
      for (int j = 1; j <= mapHeight; ++j) {
        addTile(i, j, Grass.class);
      }
    }

    enemies = new ArrayList<>();
  }

  public int getMapWidth() {
    return this.mapWidth;
  }

  public int getMapHeight() {
    return this.mapHeight;
  }

  /** 
   * get the top layer tile an the position (tileX, tileY).
   * the indices of playground tiles are in range [1, mapWidth][1, mapHeight].
   * there are a layer of edge tiles surrounding the playground (at index 0 and mapWidth/mapHeight).
   */
  public Tile getTile(int tileX, int tileY) {
    return this.map[tileX][tileY].peek();
  }

  /**
   * Put a tile layer on top of the tile stack at the position (tileX, tileY).
   * This will also set the tile's world to this world.
   */
  public void addTile(int tileX, int tileY, Tile tile) {
    tile.setWorld(this);
    this.map[tileX][tileY].push(tile);
  }

  /**
   * Construct a tile layer of class @param tileClass on top of the tile stack at the position (tileX, tileY).
   * This will also set the tile's world to this world.
   */
  public void addTile(int tileX, int tileY, Class<? extends Tile> tileClass) {
    try {
      var constructor = tileClass.getConstructor(int.class, int.class);
      Tile newTile = constructor.newInstance(tileX, tileY);
      newTile.setWorld(this);
      this.map[tileX][tileY].push(newTile);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /** 
   * remove the top layer from the tile stack at position (tileX, tileY)
   */
  public void popTile(int tileX, int tileY) {
    this.map[tileX][tileY].pop();
  }

  public Player getPlayer() {
    return this.player;
  }

  /**
   * Set the player of this world.
   * this will also set the player's world to this world.
   */
  public void setPlayer(Player player) {
    player.setWorld(this);
    this.player = player;
  }

  public Iterable<Enemy> getEnemies() {
    return this.enemies;
  }

  /**
   * Add an enemy to this world and set its world to this world.
   */
  public void addEnemy(Enemy enemy) {
    enemy.setWorld(this);
    this.enemies.add(enemy);
  }

  public void update(long dt) {
    player.update(dt);
    for (var col : this.map) {
      for (var tiles : col) {
        tiles.peek().update(dt);
      }
    }
    for (Enemy enemy : enemies) {
      enemy.update(dt);
    }
  }

  public void renderTo(GraphicsContext target) {
    for (var col : this.map) {
      for (var tiles : col) {
        for (var tile : tiles) {
          tile.renderTo(target);
        }
      }
    }
    for (Enemy enemy : enemies) {
      enemy.renderTo(target);
    }
    player.renderTo(target);
  }
}
