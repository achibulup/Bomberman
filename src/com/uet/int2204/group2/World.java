package com.uet.int2204.group2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.uet.int2204.group2.entity.Bomb;
import com.uet.int2204.group2.entity.Edge;
import com.uet.int2204.group2.entity.Enemy;
import com.uet.int2204.group2.entity.Entity;
import com.uet.int2204.group2.entity.Flame;
import com.uet.int2204.group2.entity.Grass;
import com.uet.int2204.group2.entity.Item;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.entity.Portal;
import com.uet.int2204.group2.entity.SolidTile;
import com.uet.int2204.group2.entity.Tile;
import com.uet.int2204.group2.map.SingleUseWorldTrigger;
import com.uet.int2204.group2.map.WorldTrigger;

import javafx.scene.canvas.GraphicsContext;

public class World {
  private static class TileStack extends Stack<Tile> {
  }

  private int mapWidth;
  private int mapHeight;
  private Player player;
  private TileStack[][] map;
  private List<Enemy> enemies;
  private List<WorldTrigger> triggers = new ArrayList<>();

  private boolean portalActive = false;

  public World(int mapWidth, int mapHeight) {
    this.mapWidth = mapWidth;
    this.mapHeight = mapHeight;

    this.map = new TileStack[mapWidth + 2][mapHeight + 2];
    for (int i = 0; i < mapWidth + 2; ++i) {
      for (int j = 0; j < mapHeight + 2; ++j) {
        this.map[i][j] = new TileStack();
      }
    }
    for (int i = 0; i < mapWidth + 2; ++i) {
      addTile(i, 0, new Edge(Edge.Type.TOP));
      addTile(i, mapHeight + 1, new Edge(Edge.Type.BOTTOM));
    }
    addTile(0, 0, new Edge(Edge.Type.TOP_LEFT));
    addTile(mapWidth + 1, 0, new Edge(Edge.Type.TOP_RIGHT));
    for (int i = 1; i <= mapHeight; ++i) {
      addTile(0, i, new Edge(Edge.Type.LEFT));
      addTile(mapWidth + 1, i, new Edge(Edge.Type.RIGHT));
    }
    for (int i = 1; i <= mapWidth; ++i) {
      for (int j = 1; j <= mapHeight; ++j) {
        addTile(i, j, new Grass());
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
   * Put a tile layer on top of the tile stack at the position (tile.tilex, tile.tileY).
   * This will also set the tile's world to this world.
   */
  public void addTile(Tile tile) {
    int tileX = tile.getTileX();
    int tileY = tile.getTileY();
    tile.setWorld(this);
    this.map[tileX][tileY].push(tile);
  }

  /**
   * Put a tile layer on top of the tile stack at the position (tileX, tileY).
   * This will also set the tile's world to this world.
   */
  public void addTile(int tileX, int tileY, Tile tile) {
    tile.setWorld(this);
    tile.setTileX(tileX);
    tile.setTileY(tileY);
    if (tile instanceof Portal && isPortalActive()) {
      ((Portal) tile).setBlinking(true);
    }
    this.map[tileX][tileY].push(tile);
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

  public Collection<Enemy> getEnemies() {
    return this.enemies;
  }

  /**
   * Add an enemy to this world and set its world to this world.
   */
  public void addEnemy(Enemy enemy) {
    enemy.setWorld(this);
    this.enemies.add(enemy);
  }

  public void addTrigger(WorldTrigger trigger) {
    this.triggers.add(trigger);
  }

  public Collection<WorldTrigger> getTriggers() {
    return this.triggers;
  }

  public boolean isPortalActive() {
    return this.portalActive;
  }

  public void setPortalActive() {
    this.portalActive = true;
    for (var col : this.map) {
      for (var tiles : col) {
        for (Tile tile : tiles) {
          if (tile instanceof Portal) {
            ((Portal) tile).setBlinking(true);
          }
        }
      }
    }
  }

  public void update(double dt) {
    handleInteractions();
    updateEntities(dt);
    handleInteractions();
    runTriggers();
    removeExpiredEntities();
  }

  public void renderTo(GraphicsContext target) {
    for (var col : this.map) {
      for (var tiles : col) {
        for (var tile : tiles) {
          tile.renderTo(target);
        }
      }
    }
    for (Enemy enemy : this.enemies) {
      enemy.renderTo(target);
    }
    if (this.player != null) {
      this.player.renderTo(target);
    }
  }

  protected void updateEntities(double dt) {
    if (this.player != null && !this.player.isExpired()) {
      this.player.update(dt);
    }
    for (var col : this.map) {
      for (var tiles : col) {
        for (var tile : tiles) {
          if (!tile.isExpired()) {
            tile.update(dt);
          }
        }
      }
    }
    for (Enemy enemy : this.enemies) {
      if (!enemy.isExpired()) {
        enemy.update(dt);
      }
    }
  }

  protected void handleInteractions() {
    if (this.player != null && !this.player.isExpired()) {
      playerInteractions(this.player);
    }
    for (Enemy enemy : this.enemies) {
      if (!enemy.isExpired()) {
        enemyInteractions(enemy);
      }
    }
  }

  private void runTriggers() {
    Collection<WorldTrigger> removedSingleTriggers = new ArrayList<>();
    for (WorldTrigger trigger : this.triggers) {
      if (trigger.checkCondition(this)) {
        if (trigger instanceof SingleUseWorldTrigger) {
          removedSingleTriggers.add(trigger);
        }
        trigger.activate(this);
      }
    }
    this.triggers.removeAll(removedSingleTriggers);
  }

  protected void removeExpiredEntities() {
    Collection<Entity> toBeRemoved = new ArrayList<>();
    if (player != null && player.isExpired()) {
      toBeRemoved.add(player);
      player = null;
    }

    for (var col : this.map) {
      for (var tiles : col) {
        for (int i = tiles.size(); i --> 0;) {
          Tile tile = tiles.get(i);
          if (tile.isExpired()) {
            toBeRemoved.add(tile);
          }
        }
        tiles.removeIf(Entity::isExpired);
      }
    }

    for (Enemy enemy : this.enemies) {
      if (enemy.isExpired()) {
        toBeRemoved.add(enemy);
      }
    }
    this.enemies.removeIf((enemy) -> enemy.isExpired());

    toBeRemoved.forEach(Entity::onRemoval);
  }

  protected void playerInteractions(Player player) {
    TileStack playerTile = this.map[player.getTileX()][player.getTileY()];
    for (int i = playerTile.size(); i --> 0;) {
      Tile tile = playerTile.get(i);
      if (tile instanceof SolidTile) {
        break;
      }
      if (tile instanceof Flame) {
        player.getHit();
      }
      if (tile instanceof Item) {
        player.collect((Item) tile);
      }
    }
    for (Enemy enemy : this.enemies) {
      if (enemy.getTileX() == player.getTileX() 
       && enemy.getTileY() == player.getTileY()
       && Entity.collides(enemy, player)) {
        player.getHit();
      }
    }
  }

  protected void enemyInteractions(Enemy enemy) {
    int tileX = enemy.getTileX();
    int tileY = enemy.getTileY();
    Tile tile = this.getTile(tileX, tileY);
    if (tile instanceof Flame) {
      enemy.getHit();
    }
  }
}
