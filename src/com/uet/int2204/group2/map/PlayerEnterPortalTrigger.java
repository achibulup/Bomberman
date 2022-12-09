package com.uet.int2204.group2.map;

import com.uet.int2204.group2.entity.movable.Player;
import com.uet.int2204.group2.entity.tile.Portal;
import com.uet.int2204.group2.entity.tile.Tile;

public class PlayerEnterPortalTrigger implements SingleUseWorldTrigger {
  @Override
  public boolean checkCondition(World target) {
    Player player = target.getPlayer();
    if (player == null) return false;
    Tile tile = target.getTile(player.getTileX(), player.getTileY());
    return tile instanceof Portal portal && portal.isBlinking();
  }

  @Override
  public void activate(World target) {
    Player player = target.getPlayer();
    if (player == null) {
      throw new AssertionError("Player is null!");
    }
    Tile tile = target.getTile(player.getTileX(), player.getTileY());
    if (!(tile instanceof Portal portal)) {
      throw new AssertionError(
          String.format("the player's tile: (%d, %d) is not a portal!",
                        player.getTileX(), player.getTileY()));
    }
    player.setEnteringPortal();
    player.moveTo(portal.getPixelX(), portal.getPixelY());
    portal.setBlinking(false);
  }
}
