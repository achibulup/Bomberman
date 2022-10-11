package com.uet.int2204.group2.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.uet.int2204.group2.Bomberman;
import com.uet.int2204.group2.World;
import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.controller.KeyBoardPlayerController;
import com.uet.int2204.group2.controller.KeyboardEnemyController;
import com.uet.int2204.group2.controller.RandomMoveController;
import com.uet.int2204.group2.entity.Balloom;
import com.uet.int2204.group2.entity.BombItem;
import com.uet.int2204.group2.entity.Brick;
import com.uet.int2204.group2.entity.Broom;
import com.uet.int2204.group2.entity.Enemy;
import com.uet.int2204.group2.entity.FlameItem;
import com.uet.int2204.group2.entity.Oneal;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.entity.SpeedItem;
import com.uet.int2204.group2.entity.Wall;
import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.Conversions;
import com.uet.int2204.group2.utils.Maths;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

public class GameState {
  public static int CANVAS_WIDTH = Bomberman.WIDTH;
  public static int CANVAS_HEIGHT = Bomberman.HEIGHT;
  
  private World world;
  private Canvas canvas;
  private Parent root;
  private AnimationTimer timer;
  private Collection<EventHandler<KeyEvent>> inputHandlers = new ArrayList<>();

  public GameState() {
    int mapWidth = 21; // map width in tiles
    int mapHeight = 15; // map height in tiles
    this.world = new World(mapWidth, mapHeight);
    this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    this.root = new Pane(this.canvas);

    EntityController<? super Player> playerController = new KeyBoardPlayerController(inputHandlers);
    this.world.setPlayer(new Player(1, 1, playerController));
    EntityController<? super Enemy> balloomController = RandomMoveController.INSTANCE;
    world.addEnemy(new Balloom(5, 5, balloomController));
    EntityController<? super Enemy> broomController = RandomMoveController.INSTANCE;
    world.addEnemy(new Broom(7, 9, broomController));
    EntityController<? super Enemy> onealController = new KeyboardEnemyController(inputHandlers);
    world.addEnemy(new Oneal(11, 11, onealController));

    Random rand = new Random();
    for (int i = 1; i <= mapWidth; ++i) {
      for (int j = 1; j <= mapHeight; ++j) {
        if (i + j <= 3) {
          continue;
        }
        if (i % 2 == 0 && j % 2 == 0) {
          world.addTile(i, j, Wall.class);
          continue;
        }
        int r = rand.nextInt(30);
        if (r == 8) {
          world.addTile(i, j, FlameItem.class);
          world.addTile(i, j, new Brick(i, j, true));
        } else if (r == 9) {
          world.addTile(i, j, BombItem.class);
          world.addTile(i, j, new Brick(i, j, true));
        } else if (r == 10) {
          world.addTile(i, j, SpeedItem.class);
          world.addTile(i, j, new Brick(i, j, true));
        } else if (r < 10) {
          Brick brick = new Brick(i, j);
          world.addTile(i, j, brick);
        } 
      }
    }

    this.timer = new Timer(this);
  }

  public World getWorld() {
    return this.world;
  }

  public GraphicsContext graphicsContext2D() {
    return this.canvas.getGraphicsContext2D();
  }

  public Parent getRoot() {
    return this.root;
  }

  public Iterable<EventHandler<KeyEvent>> getInputHandlers() {
    return this.inputHandlers;
  }

  public void start() {
    this.timer.start();
  }

  public void stop() {
    this.timer.stop();
  }

  private static class Timer extends AnimationTimer {
    GameState host;
    long lastTime = -1;

    public Timer(GameState host) {
      this.host = host;
    }

    @Override
    public void handle(long now) {
      double dt = this.lastTime == -1 ? 0 : Conversions.nanosToSeconds(now - this.lastTime);
      this.lastTime = now;
      host.update(dt);
      host.render();
    }
    
  }
  
  private void update(double dt) {
    world.update(dt);
    adjustCanvasView();
  }
  
  private void render() {
    GraphicsContext target = graphicsContext2D();
    target.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    world.renderTo(graphicsContext2D());
  }

  // adjust the canvas view to follow the player.
  private void adjustCanvasView() {
    Point2D mapCenter = calcMapCenter();
    double canvasCenterX = this.canvas.getWidth() / 2;
    double canvasCenterY = this.canvas.getHeight() / 2;
    graphicsContext2D().setTransform(new Affine(
        Transform.translate(canvasCenterX - mapCenter.getX(), canvasCenterY - mapCenter.getY())));
  }

  // calculate the position on the map that should be placed in the center of the canvas.
  private Point2D calcMapCenter() {
    double mapWidth = (getWorld().getMapWidth() + 2) * Constants.TILE_SIZE; // map width in pixels
    double centerX = mapWidth / 2;
    if (mapWidth > canvas.getWidth()) {
      double playerCenterX = getWorld().getPlayer().getPixelX() + Constants.TILE_SIZE / 2;
      centerX = Maths.clamp(playerCenterX, 
          canvas.getWidth() / 2, mapWidth - canvas.getWidth() / 2);
    }
    double mapHeight = (getWorld().getMapHeight() + 2) * Constants.TILE_SIZE; // like mapWidth
    double centerY = mapHeight / 2;
    if (mapHeight > canvas.getHeight()) {
      double playerCenterY = getWorld().getPlayer().getPixelY() + Constants.TILE_SIZE / 2;
      centerY = Maths.clamp(playerCenterY, 
          canvas.getHeight() / 2, mapHeight - canvas.getHeight() / 2);
    }
    return new Point2D(centerX, centerY);
  }
  
}
