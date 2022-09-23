package com.uet.int2204.group2;

import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.entity.Enemy;
import com.uet.int2204.group2.entity.StaticEntity;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class World {
  private int mapWidth;
  private int mapHeight;
  private Player player;
  private StaticEntity[][] map;
  private List<Enemy> enemies;

  public World(int mapWidth, int mapHeight) {
    this.mapWidth = mapWidth;
    this.mapHeight = mapHeight;
    this.player = new Player(0, 0);
    map = new StaticEntity[mapWidth][mapHeight];
    enemies = new ArrayList<>();
  }

  public int getMapWidth() {
    return this.mapWidth;
  }

  public int getMapHeight() {
    return this.mapHeight;
  }

  public StaticEntity getTile(int tileX, int tileY) {
    return map[tileX][tileY];
  }

  public void setTile(int tileX, int tileY, StaticEntity tile) {
    map[tileX][tileY] = tile;
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
