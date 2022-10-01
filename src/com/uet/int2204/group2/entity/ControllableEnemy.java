package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.utils.Conversions;

// a template for writing move implementation of controllable enemies, using CRTP.
// the usage is as follows
// {@code public class Ballom extends ControllableEnemy<Ballom>}
public interface ControllableEnemy {
  void correctedMove(double distance);

  boolean isAligned();

  double getSpeed();

  void control(World world);

  public default void update(long dt, World world) {
    correctedMove(getSpeed() * Conversions.nanostoSeconds(dt));
    if (isAligned()) {
      control(world);
    }
  }
}
