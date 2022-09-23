package com.uet.int2204.group2;

import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.entity.Enemy;
import com.uet.int2204.group2.entity.StaticEntity;

import java.util.ArrayList;
import java.util.List;

public class World {
  private int mapWidth;
  private int mapHeight;
  private Player player;
  private StaticEntity[][] map;
  private List<Enemy> enemies;

  World(int mapWidth, int mapHeight) {
    this.mapWidth = mapWidth;
    this.mapHeight = mapHeight;
    this.player = new Player(0, 0);
    map = new StaticEntity[mapWidth][mapHeight];
    enemies = new ArrayList<>();
  }

  public StaticEntity getTile(int tileX, int tileY) {
    return map[tileX][tileY];
  }
}
