package com.uet.int2204.group2.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.uet.int2204.group2.Bomberman;
import com.uet.int2204.group2.Sound.Sound;
import com.uet.int2204.group2.World;
import com.uet.int2204.group2.controller.AIHighMoveController;
import com.uet.int2204.group2.controller.AIMediumMoveController;
import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.controller.KeyBoardPlayerController;
import com.uet.int2204.group2.controller.RandomMoveController;
import com.uet.int2204.group2.entity.Balloom;
import com.uet.int2204.group2.entity.Bear;
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

import com.uet.int2204.group2.utils.ResourceManager;
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
  Sound sound = new Sound();
  public static int CANVAS_WIDTH = Bomberman.WIDTH;
  public static int CANVAS_HEIGHT = Bomberman.HEIGHT;
  
  private World world;
  private Canvas canvas;
  private Parent root;
  private AnimationTimer gameLoop;
  private Collection<EventHandler<KeyEvent>> inputHandlers = new ArrayList<>();

  public GameState() {
    int mapWidth = 21; // map width in tiles
    int mapHeight = 15; // map height in tiles
    this.world = new World(mapWidth, mapHeight);
    this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    this.root = new Pane(this.canvas);

    Random rand = new Random();

    EntityController<? super Player> playerController = new KeyBoardPlayerController(inputHandlers);
    EntityController<? super Enemy> balloomController = RandomMoveController.INSTANCE;
    EntityController<? super Enemy> broomController = RandomMoveController.INSTANCE;
    EntityController<? super Enemy> bearController = AIHighMoveController.INSTANCE;
    EntityController<? super Enemy> onealController = AIMediumMoveController.INSTANCE;
    this.world.setPlayer(new Player(1, 1, playerController));

//    for (int i = 0; i < 3; ++i) {
//      this.world.addEnemy(new Balloom(rand.nextInt(mapWidth) + 1, rand.nextInt(mapHeight) + 1, balloomController));
//      this.world.addEnemy(new Oneal(rand.nextInt(mapWidth) + 1, rand.nextInt(mapHeight) + 1, onealController));
//    }
//
//    for (int i = 0; i < 8; ++i) {
//      this.world.addEnemy(new Bear(rand.nextInt(mapWidth) + 1, rand.nextInt(mapHeight) + 1, bearController));
//    }

    for (int i = 0; i < 5; ++i) {
      this.world.addEnemy(new Broom(rand.nextInt(mapWidth) + 1, rand.nextInt(mapHeight) + 1, broomController));
    }

    for (int i = 1; i <= mapWidth; ++i) {
      for (int j = 1; j <= mapHeight; ++j) {
        if (i + j <= 3) {
          continue;
        }
        if (i % 2 == 0 && j % 2 == 0) {
          world.addTile(i, j, new Wall());
          continue;
        }
        int r = rand.nextInt(80);
        if (r == 8) {
          world.addTile(i, j, new FlameItem());
          world.addTile(i, j, new Brick(true));
        } else if (r == 9) {
          world.addTile(i, j, new BombItem());
          world.addTile(i, j, new Brick(true));
        } else if (r == 10) {
          world.addTile(i, j, new SpeedItem());
          world.addTile(i, j, new Brick(true));
        } else if (r < 20) {
          world.addTile(i, j, new Brick());
        } 
      }
    }

    this.gameLoop = new GameLoop(this);
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
    Bomberman.start.stopMusic();
    Bomberman.start.playMusic(ResourceManager.sound[1]);
    this.gameLoop.start();
  }

  public void stop() {
    this.gameLoop.stop();
  }

  private static class GameLoop extends AnimationTimer {
    GameState host;
    long lastTime = -1;

    public GameLoop(GameState host) {
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
    if (this.world.getPlayer() == null) {
      Bomberman.closeApp();
    }
    GraphicsContext target = graphicsContext2D();
    target.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    world.renderTo(graphicsContext2D());
  }

  // adjust the canvas view to follow the player.
  private void adjustCanvasView() {
    if (getWorld().getPlayer() == null) {
      return;
    }
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
