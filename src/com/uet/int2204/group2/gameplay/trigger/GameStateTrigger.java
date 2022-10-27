package com.uet.int2204.group2.gameplay.trigger;

import com.uet.int2204.group2.gameplay.GameState;

public interface GameStateTrigger {
  boolean checkCondition(GameState target);

  void activate(GameState target);
}
