package com.uet.int2204.group2.utils;

import java.nio.file.FileSystemNotFoundException;

import com.uet.int2204.group2.graphics.AnimationData;
import com.uet.int2204.group2.graphics.Sprite;

import javafx.scene.image.Image;

public class ResourceManager {
  public static final Image background;

  public static final Sprite grassNormal;
  public static final Sprite grassShadowed;

  public static final Sprite brick;
  public static final AnimationData brickSparky;
  public static final AnimationData brickExplosion;

  public static final Sprite wall;

  public static final Sprite topEdge;
  public static final Sprite topLeftEdge;
  public static final Sprite topRightEdge;
  public static final Sprite leftEdge;
  public static final Sprite rightEdge;
  public static final Sprite bottomEdge;

  public static final AnimationData itemExplosion;
  public static final AnimationData flameItem;
  public static final AnimationData bombItem;
  public static final AnimationData speedItem;

  public static final AnimationData playerIdleUp;
  public static final AnimationData playerIdleDown;
  public static final AnimationData playerIdleLeft;
  public static final AnimationData playerIdleRight;

  public static final AnimationData playerWalkUp;
  public static final AnimationData playerWalkDown;
  public static final AnimationData playerWalkLeft;
  public static final AnimationData playerWalkRight;

  public static final AnimationData bomb;
  public static final AnimationData upFlame;
  public static final AnimationData downFlame;
  public static final AnimationData leftFlame;
  public static final AnimationData rightFlame;
  public static final AnimationData horizontalFlame;
  public static final AnimationData verticalFlame;
  public static final AnimationData centerFlame;

  public static final AnimationData balloom;

  public static final AnimationData oneal;

  public static final AnimationData broom;

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

  public static Sprite[] tryLoadSpriteSheet(String path, int spriteWidth, int spriteHeight) {
    return Sprite.makeSpriteSheet(tryLoadImage(path), spriteWidth, spriteHeight);
  }

  static {
    background = tryLoadImage("sprites/background.png");

    Sprite[] grassSheet = tryLoadSpriteSheet("sprites/map/grass@2.png");
    grassNormal = grassSheet[0];
    grassShadowed = grassSheet[1];

    Sprite[] bricks = tryLoadSpriteSheet("sprites/map/brick_sparky@2.png");
    brick = bricks[0];
    brickSparky = new AnimationData(bricks, 0.6);

    Sprite[] brickExplosionSheet = tryLoadSpriteSheet("sprites/map/brick_explosion@7.png");
    brickExplosion = new AnimationData(brickExplosionSheet, 0.07, 1);

    Sprite wallImg = new Sprite(tryLoadImage("sprites/map/wall@1.png"));
    wall = wallImg;
    
    Sprite[] edgesSheet = tryLoadSpriteSheet("sprites/map/edges@10.png");
    topEdge = edgesSheet[2];
    topLeftEdge = edgesSheet[1];
    topRightEdge = edgesSheet[3];
    leftEdge = edgesSheet[0];
    rightEdge = edgesSheet[4];
    bottomEdge = edgesSheet[5];

    Sprite[] itemExplosionSheet = tryLoadSpriteSheet(
        "sprites/powerup/powerup_explosion@7.png", 52, 56);
    itemExplosion = new AnimationData(itemExplosionSheet, 0.075, 1);

    Sprite[] flameItemSheet = tryLoadSpriteSheet("sprites/powerup/longer_flame@2.png");
    flameItem = new AnimationData(flameItemSheet, 0.4);

    Sprite[] bombItemSheet = tryLoadSpriteSheet("sprites/powerup/extra_bomb@2.png");
    bombItem = new AnimationData(bombItemSheet, 0.4);
    
    Sprite[] speedItemSheet = tryLoadSpriteSheet("sprites/powerup/bonus_speed@2.png");
    speedItem = new AnimationData(speedItemSheet, 0.4);

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
    
    Sprite[] bombSheet = tryLoadSpriteSheet("sprites/bomb/bomb@4.png");
    bomb = new AnimationData(bombSheet, 0.4);
    
    Sprite[] upFlameSheet = tryLoadSpriteSheet("sprites/bomb/explosion_up@6.png");
    upFlame = new AnimationData(upFlameSheet, 0.15, 1);
    Sprite[] downFlameSheet = tryLoadSpriteSheet("sprites/bomb/explosion_down@6.png");
    downFlame = new AnimationData(downFlameSheet, 0.15, 1);
    Sprite[] leftFlameSheet = tryLoadSpriteSheet("sprites/bomb/explosion_left@6.png");
    leftFlame = new AnimationData(leftFlameSheet, 0.15, 1);
    Sprite[] rightFlameSheet = tryLoadSpriteSheet("sprites/bomb/explosion_right@6.png");
    rightFlame = new AnimationData(rightFlameSheet, 0.15, 1);
    Sprite[] horizontalFlameSheet = tryLoadSpriteSheet("sprites/bomb/explosion_horizontal@6.png");
    horizontalFlame = new AnimationData(horizontalFlameSheet, 0.15, 1);
    Sprite[] verticalFlameSheet = tryLoadSpriteSheet("sprites/bomb/explosion_vertical@6.png");
    verticalFlame = new AnimationData(verticalFlameSheet, 0.15, 1);
    Sprite[] centerFlameSheet = tryLoadSpriteSheet("sprites/bomb/explosion_center@6.png");
    centerFlame = new AnimationData(centerFlameSheet, 0.15, 1);

    Sprite[] balloomSheet = tryLoadSpriteSheet("sprites/enemy/balloom@3.png");
    balloom = new AnimationData(balloomSheet);

    Sprite[] onealSheet = tryLoadSpriteSheet("sprites/enemy/oneal@4.png");
    oneal = new AnimationData(onealSheet);

    Sprite[] broomSheet = tryLoadSpriteSheet("sprites/enemy/broom@4.png");
    broom = new AnimationData(broomSheet);
  }
}
