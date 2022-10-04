package com.uet.int2204.group2.utils;

import java.nio.file.FileSystemNotFoundException;

import com.uet.int2204.group2.graphics.AnimationData;
import com.uet.int2204.group2.graphics.Sprite;

import javafx.scene.image.Image;

public class ResourceManager {
  public static final Sprite grassNormal;
  public static final Sprite grassShadowed;

  public static final Sprite brick;
  public static final AnimationData brickSparky;

  public static final Sprite wall;

  public static final Sprite topEdge;
  public static final Sprite topLeftEdge;
  public static final Sprite topRightEdge;
  public static final Sprite leftEdge;
  public static final Sprite rightEdge;
  public static final Sprite bottomEdge;

  public static final AnimationData bomb;

  public static final AnimationData playerIdleUp;
  public static final AnimationData playerIdleDown;
  public static final AnimationData playerIdleLeft;
  public static final AnimationData playerIdleRight;

  public static final AnimationData playerWalkUp;
  public static final AnimationData playerWalkDown;
  public static final AnimationData playerWalkLeft;
  public static final AnimationData playerWalkRight;

  public static final AnimationData balloom;

  public static final AnimationData oneal;
  // call this function to force initialization of the class, thereby load all the resources
  public static void load() {
  }

  public static Image tryLoadImage(String path) {
    Image image = new Image("file:target/classes/" + path);
    if (image.isError()) {
      throw new FileSystemNotFoundException("file: " + path + " not found.");
    }
    return image;
  }

  public static Sprite[] tryLoadSpriteSheet(String path) {
    return Sprite.makeSpriteSheet(tryLoadImage(path), Constants.TILE_SIZE, Constants.TILE_SIZE);
  }

  static {
    Sprite[] grassSheet = tryLoadSpriteSheet("sprites/map/grass@2.png");
    grassNormal = grassSheet[0];
    grassShadowed = grassSheet[1];

    Sprite[] bricks = tryLoadSpriteSheet("sprites/map/brick_sparky@2.png");
    brick = bricks[0];
    brickSparky = new AnimationData(bricks, Conversions.secondsToNanos(0.6));

    Sprite wallImg = new Sprite(tryLoadImage("sprites/map/wall@1.png"));
    wall = wallImg;
    
    Sprite[] edgesSheet = tryLoadSpriteSheet("sprites/map/edges@10.png");
    topEdge = edgesSheet[2];
    topLeftEdge = edgesSheet[1];
    topRightEdge = edgesSheet[3];
    leftEdge = edgesSheet[0];
    rightEdge = edgesSheet[4];
    bottomEdge = edgesSheet[5];

    Sprite[] bombSheet = tryLoadSpriteSheet("sprites/bomb@4.png");
    bomb = new AnimationData(bombSheet, Conversions.secondsToNanos(0.4));

    playerIdleUp = new AnimationData(tryLoadSpriteSheet("sprites/player/idle_up@1.png"));
    playerIdleDown = new AnimationData(tryLoadSpriteSheet("sprites/player/idle_down@1.png"));
    playerIdleLeft = new AnimationData(tryLoadSpriteSheet("sprites/player/idle_left@1.png"));
    playerIdleRight = new AnimationData(tryLoadSpriteSheet("sprites/player/idle_right@1.png"));
    
    Sprite[] playerWalkUpSheet = tryLoadSpriteSheet("sprites/player/walking_up@4.png");
    playerWalkUp = new AnimationData(playerWalkUpSheet);

    Sprite[] playerWalkDownSheet = tryLoadSpriteSheet("sprites/player/walking_down@4.png");
    playerWalkDown = new AnimationData(playerWalkDownSheet);
    
    Sprite[] playerWalkLeftSheet = tryLoadSpriteSheet("sprites/player/walking_left@4.png");
    playerWalkLeft = new AnimationData(playerWalkLeftSheet);
    
    Sprite[] playerWalkRightSheet = tryLoadSpriteSheet("sprites/player/walking_right@4.png");
    playerWalkRight = new AnimationData(playerWalkRightSheet);

    Sprite[] balloomSheet = tryLoadSpriteSheet("sprites/enemy/balloom@3.png");
    balloom = new AnimationData(balloomSheet);

    Sprite[] onealSheet = tryLoadSpriteSheet("sprites/enemy/oneal@4.png");
    oneal = new AnimationData(onealSheet);
  }
}
