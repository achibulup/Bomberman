package com.uet.int2204.group2.gameplay.trigger;

import com.uet.int2204.group2.gameplay.GameState;
import com.uet.int2204.group2.map.PlayerEnterPortalTrigger;

public class NextLevelTrigger implements GameStateTrigger {
  @Override
  public boolean checkCondition(GameState target) {
    for (var trigger : target.getWorld().getTriggers()) {
      if (trigger instanceof PlayerEnterPortalTrigger) {
        return false;
      }
    }
    return target.getWorld().getPlayer() == null;
  }

  @Override
  public void activate(GameState target) {
    target.nextLevel();
//    Sound sound = new Sound();
//    sound.playMusic(ResourceManager.sound[6]);
  };
}
