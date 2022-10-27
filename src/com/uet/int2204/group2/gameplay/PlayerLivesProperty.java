package com.uet.int2204.group2.gameplay;

import com.uet.int2204.group2.entity.movable.Player;
import com.uet.int2204.group2.utils.IntProperty;

public class PlayerLivesProperty implements IntProperty {
  private Player host;

  public PlayerLivesProperty(Player host) {
    this.host = host;
  }

  @Override
  public int get() {
    return host.getLives();
  }

  @Override
  public void set(int value) {
    host.setLives(value);
  }
}
