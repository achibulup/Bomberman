package com.uet.int2204.group2.utils;

import java.nio.file.FileSystemNotFoundException;

import com.uet.int2204.group2.graphics.Sprite;

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

  public static final Sprite[] playerWalkDown;

  // call this function to force initialization of the class, thereby load all the resources
  public static void load() {
  }

  public static Image tryLoadImage(String path) {
    // try {
    //   return new Image(new FileInputStream(path));
    // } catch (Exception e) {
    //   throw new RuntimeException("cannot open file: " + path, e);
    // }
    Image image = new Image("file:target/classes/" + path);
    if (image.isError()) {
      throw new FileSystemNotFoundException("file: " + path + " not found.");
    }
    return image;
  }

  static {
    Sprite[] grassSheet = Sprite.makeSpriteSheet(
      tryLoadImage("sprites/map/grass@2.png"), Constants.TILE_SIZE, Constants.TILE_SIZE);
    Sprite[] bricks = Sprite.makeSpriteSheet(
        tryLoadImage("sprites/map/brick_sparky@2.png"), Constants.TILE_SIZE, Constants.TILE_SIZE);
    Sprite wallImg = new Sprite(tryLoadImage("sprites/map/wall@1.png"));
    Sprite[] edgesSheet = Sprite.makeSpriteSheet(
      tryLoadImage("sprites/map/edges@10.png"), Constants.TILE_SIZE, Constants.TILE_SIZE);
    Sprite playerIdleUpImg = new Sprite(tryLoadImage("sprites/player/idle_up@1.png"));
    Sprite playerIdleDownImg = new Sprite(tryLoadImage("sprites/player/idle_down@1.png"));
    Sprite playerIdleLeftImg = new Sprite(tryLoadImage("sprites/player/idle_left@1.png"));
    Sprite playerIdleRightImg = new Sprite(tryLoadImage("sprites/player/idle_right@1.png"));
    Sprite[] playerWalkDownSheet = Sprite.makeSpriteSheet(
      tryLoadImage("sprites/player/walking_down@4.png"), Constants.TILE_SIZE, Constants.TILE_SIZE);


    grassNormal = grassSheet[0];
    grassShadowed = grassSheet[1];
    brick = bricks[0];
    brickSparky = bricks[1];
    wall = wallImg;
    topEdge = edgesSheet[2];
    topLeftEdge = edgesSheet[1];
    topRightEdge = edgesSheet[3];
    leftEdge = edgesSheet[0];
    rightEdge = edgesSheet[4];
    bottomEdge = edgesSheet[5];
    playerIdleUp = playerIdleUpImg;
    playerIdleDown = playerIdleDownImg;
    playerIdleLeft = playerIdleLeftImg;
    playerIdleRight = playerIdleRightImg;
    playerWalkDown = playerWalkDownSheet;
  }
}
