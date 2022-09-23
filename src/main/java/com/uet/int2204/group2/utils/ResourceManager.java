package com.uet.int2204.group2.utils;

import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.Constants;

import javafx.scene.image.Image;

public class ResourceManager {
  public static Sprite grassNormal;
  public static Sprite grassShadowed;

  public static Sprite playerIdleUp;
  public static Sprite playerIdleDown;
  public static Sprite playerIdleLeft;
  public static Sprite playerIdleRight;

  static {
    Sprite[] grass = Sprite.makeSpriteSheet(
      new Image("res/sprites/map/grass@2.png"), Constants.TILE_SIZE, Constants.TILE_SIZE);
    grassNormal = grass[0];
    grassShadowed = grass[1];
    

    Sprite[] playerIdle = Sprite.makeSpriteSheet(
        new Image("res/sprites/player/idle@4.png"), Constants.TILE_SIZE, Constants.TILE_SIZE);
    playerIdleUp = playerIdle[0];
    playerIdleDown = playerIdle[2];
    playerIdleLeft = playerIdle[1];
    playerIdleRight = playerIdle[3];
  }
}
