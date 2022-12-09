package com.uet.int2204.group2.entity.tile;

import java.util.EnumMap;
import java.util.Map;

import com.uet.int2204.group2.entity.movable.Player;
import com.uet.int2204.group2.entity.tile.Flame.Type;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.menu.GameMenu;
import com.uet.int2204.group2.utils.Direction;
import com.uet.int2204.group2.utils.ResourceManager;

public class Bomb extends Tile implements SolidTile, DestroyableTile {
  public static final double BOMB_EXPLODE_DELAY = 2;

  private static Map<Direction, Flame.Type> flameMids = new EnumMap<>(Direction.class);
  private static Map<Direction, Flame.Type> flameEnds = new EnumMap<>(Direction.class);

  static {
    flameMids.put(Direction.UP, Type.VERTICAL);
    flameMids.put(Direction.DOWN, Type.VERTICAL);
    flameMids.put(Direction.LEFT, Type.HORIZONTAL);
    flameMids.put(Direction.RIGHT, Type.HORIZONTAL);
    
    flameEnds.put(Direction.UP, Type.UP);
    flameEnds.put(Direction.DOWN, Type.DOWN);
    flameEnds.put(Direction.LEFT, Type.LEFT);
    flameEnds.put(Direction.RIGHT, Type.RIGHT);
  }

  private Animation animation = new Animation(ResourceManager.bomb);
  private Player owner;
  private double timer = BOMB_EXPLODE_DELAY;

  public Bomb(Player owner) {
    this.owner = owner;
  }

  public Bomb(int x, int y, Player owner) {
    super(x, y);
    this.owner = owner;
  }

  public Player getOwner() {
    return this.owner;
  }

  @Override
  public Sprite getSprite() {
    return this.animation.currentSprite();
  }

  @Override
  public void update(double dt) {
    this.animation.update(dt);
    this.timer -= dt;
    if (this.timer <= 0) {
      explode();
    }
  }

  @Override 
  public void onRemoval() {
    getWorld().addTile(getTileX(), getTileY(), new Flame(Flame.Type.CENTER));
    int length = getOwner().getFlameLength();
    
    for (Direction direction : Direction.values()) {
      if (direction == Direction.NONE) {
        continue;
      }
      int strength = getOwner().getFlameStrength();
      // up direction
      for (int i = 1; i <= length; ++i) {
        int tileX = getTileX() + direction.x * i;
        int tileY = getTileY() + direction.y * i;
        Tile nextTile = getOwner().getWorld().getTile(tileX, tileY);
        if (nextTile instanceof Portal) {
          break;
        }
        if (nextTile instanceof DestroyableTile tile) {
          tile.destroy();
          if (strength == 0) {
            break;
          }
          --strength;
        } else if (nextTile instanceof SolidTile) {
          break;
        }
        if (i == length) {
          getWorld().addTile(tileX, tileY, new Flame(flameEnds.get(direction)));
        } else {
          getWorld().addTile(tileX, tileY, new Flame(flameMids.get(direction)));
        }
      }
    }
  }

  @Override
  public void destroy() {
    //Sound sound = new Sound();
    GameMenu.sound.playMusic(ResourceManager.sound[3], true);
    explode();
  }

  public void explode() {
    //Sound sound = new Sound();
    GameMenu.sound.playMusic(ResourceManager.sound[3], true);
    markExpired();
  }
}
