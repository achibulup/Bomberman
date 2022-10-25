package com.uet.int2204.group2.component;

public interface GameStateTrigger {
  boolean checkCondition(GameState target);

  void activate(GameState target);
}
