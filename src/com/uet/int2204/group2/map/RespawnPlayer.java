package com.uet.int2204.group2.map;

import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.Direction;
import com.uet.int2204.group2.utils.IntProperty;

public class RespawnPlayer extends WorldExtension {
  public static final double PLAYER_RESPAWN_DELAY = 0.7;

  private int respawnX;
  private int respawnY;
  private IntProperty livesProperty;
  private double timer;
  private Player playerToBeRespawned;

  public RespawnPlayer() {
  }

  public RespawnPlayer(int respawnX, int respawnY) {
    setRespawnX(respawnX);
    setRespawnY(respawnY);
  }

  public int getRespawnX() {
    return this.respawnX;
  }

  public void setRespawnX(int respawnX) {
    this.respawnX = respawnX;
  }

  public int getRespawnY() {
    return this.respawnY;
  }

  public void setRespawnY(int respawnY) {
    this.respawnY = respawnY;
  }

  public IntProperty getLivesProperty() {
    return this.livesProperty;
  }

  public void setLivesProperty(IntProperty livesProperty) {
    this.livesProperty = livesProperty;
  }

  @Override
  public void update(double dt) {
    if (isWaitingToRespawn()) {
      this.timer -= dt;
      if (this.timer <= 0) {
        respawnPlayer();
      }
    } else {
      Player player = getWorld().getPlayer();
      if (player != null && player.isExpired() && getLivesProperty().get() > 0) {
        this.playerToBeRespawned = player;
        this.timer = PLAYER_RESPAWN_DELAY;
        getWorld().setPlayer(null);
      }
    }
  }

  private boolean isWaitingToRespawn() {
    return this.playerToBeRespawned != null;
  }

  private void respawnPlayer() {
    this.playerToBeRespawned.setExpired(false);
    this.playerToBeRespawned.setDying(false);
    this.playerToBeRespawned.setDirection(Direction.DOWN);
    this.playerToBeRespawned.setDirection(Direction.NONE);
    this.playerToBeRespawned.moveTo(getRespawnX() * Constants.TILE_SIZE, 
                                    getRespawnY() * Constants.TILE_SIZE);
    getWorld().setPlayer(this.playerToBeRespawned);
    this.playerToBeRespawned = null;
    getLivesProperty().set(getLivesProperty().get() - 1);
  }
}
