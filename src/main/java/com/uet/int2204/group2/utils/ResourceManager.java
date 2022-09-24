package com.uet.int2204.group2.utils;

import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.Constants;

import javafx.scene.image.Image;

public class ResourceManager {
  public static final Sprite grassNormal;
  public static final Sprite grassShadowed;

  public static final Sprite brick;
  public static final Sprite brickSparky;

  public static final Sprite wall;

  public static final Sprite topEdge;
  public static final Sprite topLeftEdge;
  public static final Sprite topRightEdge;
  public static final Sprite leftEdge;
  public static final Sprite rightEdge;
  public static final Sprite bottomEdge;

  public static final Sprite playerIdleUp;
  public static final Sprite playerIdleDown;
  public static final Sprite playerIdleLeft;
  public static final Sprite playerIdleRight;

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

    Sprite[] edges = Sprite.makeSpriteSheet(
      new Image("file:target/classes/sprites/map/edges@10.png"), Constants.TILE_SIZE, Constants.TILE_SIZE);
    topEdge = edges[2];
    topLeftEdge = edges[1];
    topRightEdge = edges[3];
    leftEdge = edges[0];
    rightEdge = edges[4];
    bottomEdge = edges[5];

    playerIdleUp = new Sprite(new Image("file:target/classes/sprites/player/idle_up@1.png"));
    playerIdleDown = new Sprite(new Image("file:target/classes/sprites/player/idle_down@1.png"));
    playerIdleLeft = new Sprite(new Image("file:target/classes/sprites/player/idle_left@1.png"));
    playerIdleRight = new Sprite(new Image("file:target/classes/sprites/player/idle_right@1.png"));
  }
}
