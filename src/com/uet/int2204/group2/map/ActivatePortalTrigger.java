package com.uet.int2204.group2.map;

public class ActivatePortalTrigger implements SingleUseWorldTrigger {
  @Override
  public boolean checkCondition(World target) {
    return target.getEnemies().size() == 0;
  }

  @Override
  public void activate(World target) {
    target.setPortalActive();
  }
}
