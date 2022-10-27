package com.uet.int2204.group2.map;

public interface WorldTrigger {
  boolean checkCondition(World target);

  void activate(World target);

  default boolean isDone() {
    return false;
  }
}
