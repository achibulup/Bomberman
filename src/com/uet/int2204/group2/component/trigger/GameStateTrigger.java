package com.uet.int2204.group2.component.trigger;

import com.uet.int2204.group2.component.GameState;

public interface GameStateTrigger {
  boolean checkCondition(GameState target);

  void activate(GameState target);
}
