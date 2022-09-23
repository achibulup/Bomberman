package com.uet.int2204.group2.utils;

import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.Constants;

import javafx.scene.image.Image;

public class ResourceManager {
  public static Sprite grassNormal;
  public static Sprite grassShadowed;

  public static Sprite brick;
  public static Sprite brickSparky;

  public static Sprite wall;

  public static Sprite playerIdleUp;
  public static Sprite playerIdleDown;
  public static Sprite playerIdleLeft;
  public static Sprite playerIdleRight;

  static {
    Sprite[] grass = Sprite.makeSpriteSheet(
        new Image("file:target/classes/sprites/map/grass@2.png"), Constants.TILE_SIZE, Constants.TILE_SIZE);
    grassNormal = grass[0];
    grassShadowed = grass[1];

    Sprite[] bricks = Sprite.makeSpriteSheet(
        new Image("file:target/classes/sprites/map/brick_sparky@2.png"), Constants.TILE_SIZE, Constants.TILE_SIZE);
    brick = bricks[0];
    brickSparky = bricks[1];

    wall = new Sprite(new Image("file:target/classes/sprites/map/wall@1.png"));

    playerIdleUp = new Sprite(new Image("file:target/classes/sprites/player/idle_up@1.png"));
    playerIdleDown = new Sprite(new Image("file:target/classes/sprites/player/idle_down@1.png"));
    playerIdleLeft = new Sprite(new Image("file:target/classes/sprites/player/idle_left@1.png"));
    playerIdleRight = new Sprite(new Image("file:target/classes/sprites/player/idle_right@1.png"));
  }
}
