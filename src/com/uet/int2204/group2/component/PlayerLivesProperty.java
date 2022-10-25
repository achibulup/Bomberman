package com.uet.int2204.group2.component;

import com.uet.int2204.group2.utils.IntProperty;

public class PlayerLivesProperty implements IntProperty {
  private GameState host;

  public PlayerLivesProperty(GameState host) {
    this.host = host;
  }

  @Override
  public int get() {
    return host.playerLives;
  }

  @Override
  public void set(int value) {
    host.playerLives = value;
  }
}
