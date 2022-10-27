package com.uet.int2204.group2.gameplay;

import com.uet.int2204.group2.utils.Conversions;
import javafx.animation.AnimationTimer;

class GameLoop extends AnimationTimer {
  GameState host;
  long lastTime = -1;

  public GameLoop(GameState host) {
    this.host = host;
  }

  @Override
  public void start() {
    lastTime = -1;
    super.start();
  }

  @Override
  public void handle(long now) {
    double dt = this.lastTime == -1 ? 0 : Conversions.nanosToSeconds(now - this.lastTime);
    this.lastTime = now;
    host.update(dt);
    host.render();
  }
}