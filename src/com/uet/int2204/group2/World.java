package com.uet.int2204.group2;

import java.util.ArrayList;
import java.util.List;

import com.uet.int2204.group2.entity.Edge;
import com.uet.int2204.group2.entity.Enemy;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.entity.StaticEntity;

import javafx.scene.canvas.GraphicsContext;

public class World {
  private int mapWidth;
  private int mapHeight;
  private Player player;
  private StaticEntity[][] map;
  private List<Enemy> enemies;

  public World(int mapWidth, int mapHeight) {
    this.mapWidth = mapWidth;
    this.mapHeight = mapHeight;
    this.player = new Player(1, 1);

    map = new StaticEntity[mapWidth + 2][mapHeight + 2];
    for (int i = 0; i < mapWidth + 2; ++i) {
      map[i][0] = new Edge(i, 0, Edge.Type.TOP);
      map[i][mapHeight + 1] = new Edge(i, mapHeight + 1, Edge.Type.BOTTOM);
    }
    map[0][0] = new Edge(0, 0, Edge.Type.TOP_LEFT);
    map[mapWidth + 1][0] = new Edge(mapWidth + 1, 0, Edge.Type.TOP_RIGHT);
    for (int i = 1; i <= mapHeight; ++i) {
      map[0][i] = new Edge(0, i, Edge.Type.LEFT);
      map[mapWidth + 1][i] = new Edge(mapWidth + 1, i, Edge.Type.RIGHT);
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
   * get the tile an the position (tileX, tileY).
   * the indices of playground tiles are in range [1, mapWidth][1, mapHeight].
   * there are a layer of edge tiles surrounding the playground (at index 0 and mapWidth/mapHeight).
   */
  public StaticEntity getTile(int tileX, int tileY) {
    return map[tileX][tileY];
  }

  public void setTile(int tileX, int tileY, StaticEntity tile) {
    map[tileX][tileY] = tile;
  }

  public Player getPlayer() {
    return this.player;
  }

  public void update(long dt) {
    player.update(dt, this);
    for (StaticEntity[] row : map) {
      for (StaticEntity tile : row) {
        tile.update(dt, this);
      }
    }
    for (Enemy enemy : enemies) {
      enemy.update(dt, this);
    }
  }

  public void renderTo(GraphicsContext target) {
    for (StaticEntity[] row : map) {
      for (StaticEntity tile : row) {
        tile.renderTo(target);
      }
    }
    for (Enemy enemy : enemies) {
      enemy.renderTo(target);
    }
    player.renderTo(target);
  }
}
