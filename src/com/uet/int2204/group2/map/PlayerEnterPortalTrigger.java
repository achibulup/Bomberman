package com.uet.int2204.group2.map;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.entity.Portal;
import com.uet.int2204.group2.entity.Tile;

public class PlayerEnterPortalTrigger implements SingleUseWorldTrigger {
  @Override
  public boolean checkCondition(World target) {
    Player player = target.getPlayer();
    Tile tile = target.getTile(player.getTileX(), player.getTileY());
    return player != null && tile instanceof Portal && ((Portal) tile).isBlinking();
  }

  @Override
  public void activate(World target) {
    Player player = target.getPlayer();
    if (player == null) {
      throw new AssertionError("Player is null!");
    }
    Tile portal = target.getTile(player.getTileX(), player.getTileY());
    if (!(portal instanceof Portal)) {
      throw new AssertionError(
          String.format("the player's tile: (%d, %d) is not a portal!",
                        player.getTileX(), player.getTileY()));
    }
    player.setEnteringPortal();
    player.moveTo(portal.getPixelX(), portal.getPixelY());
    ((Portal) portal).setBlinking(false);
  }
}
