package com.uet.int2204.group2.map;

import com.uet.int2204.group2.World;

public interface WorldTrigger {
  boolean checkCondition(World target);

  void activate(World target);

  default boolean isDone() {
    return false;
  }
}
