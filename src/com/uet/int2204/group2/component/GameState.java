package com.uet.int2204.group2.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Timer;

import com.uet.int2204.group2.Bomberman;
import com.uet.int2204.group2.World;
import com.uet.int2204.group2.controller.*;
import com.uet.int2204.group2.controller.Algorithm.AIIntelligent;
import com.uet.int2204.group2.entity.*;
import com.uet.int2204.group2.map.ActivatePortalTrigger;
import com.uet.int2204.group2.map.BlinkBrickTrigger;
import com.uet.int2204.group2.map.PlayerEnterPortalTrigger;
import com.uet.int2204.group2.map.RespawnPlayer;
import com.uet.int2204.group2.map.MapData;
import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.Conversions;
import com.uet.int2204.group2.utils.Maths;

import com.uet.int2204.group2.utils.ResourceManager;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

public class GameState {
  public static int CANVAS_WIDTH = Bomberman.WIDTH;
  public static int CANVAS_HEIGHT = Bomberman.HEIGHT;

  public static final int PLAYER_LIVES = 4;

  private World world;
  private Canvas canvas;
  private Parent root;
  private AnimationTimer gameLoop;

  private Collection<EventHandler<KeyEvent>> inputHandlers = new ArrayList<>();
  private Collection<GameStateTrigger> triggers = new ArrayList<>();

  public Text point = new Text();
  public Text timer = new Text();
  public int timesLeft = 540 * 60;
  public int init_point = 0;
  public int level_played = 0;
  public Text lives = new Text();
  public Text namePlayer = new Text();

  int currentLevel;
  int playerLives;

  public GameState() {
    int mapWidth = 21; // map width in tiles
    int mapHeight = 15; // map height in tiles
    this.world = new World(mapWidth, mapHeight);
    this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    this.canvas.setTranslateX(0);
    this.canvas.setTranslateY(48 + 8);
    this.root = new Pane(this.canvas);
    setPoint((Pane) getRoot(), point, timer, lives, namePlayer);

    this.playerLives = PLAYER_LIVES;
    loadMap(currentLevel = 1);
    KeyboardLevelController levelController = new KeyboardLevelController();
    this.inputHandlers.add(levelController);
    this.triggers.add(levelController);
    this.triggers.add(new NextLevelTrigger());
    this.gameLoop = new GameLoop(this);

//     Random rand = new Random();

//     EntityController<? super Player> playerController = new KeyBoardPlayerController(inputHandlers);
//     EntityController<? super Enemy> balloomController = AILowMoveController.INSTANCE;
//     EntityController<? super Enemy> broomController = AIIntelligent.INSTANCE;
//     EntityController<? super Enemy> bearController = AIHighMoveController.INSTANCE;
//     EntityController<? super Enemy> onealController = AIIntelligent.INSTANCE;
//     this.world.setPlayer(new Player(1, 1, playerController));

//     for (int i = 0; i < 3; ++i) {
// //      this.world.addEnemy(new Balloom(rand.nextInt(mapWidth) + 1, rand.nextInt(mapHeight) + 1, balloomController));
//       this.world.addEnemy(new Oneal(rand.nextInt(mapWidth) + 1, rand.nextInt(mapHeight) + 1, onealController));
//     }

//     for (int i = 0; i < 8; ++i) {
// //      this.world.addEnemy(new Bear(rand.nextInt(mapWidth) + 1, rand.nextInt(mapHeight) + 1, bearController));
//     }

//     for (int i = 0; i < 5; ++i) {
//       this.world.addEnemy(new Broom(rand.nextInt(mapWidth) + 1, rand.nextInt(mapHeight) + 1, broomController));
//     }

//     for (int i = 1; i <= mapWidth; ++i) {
//       for (int j = 1; j <= mapHeight; ++j) {
//         if (i + j <= 3) {
//           continue;
//         }
//         if (i % 2 == 0 && j % 2 == 0) {
//           world.addTile(i, j, new Wall());
//           continue;
//         }
//         int r = rand.nextInt(80);
//         if (r == 8) {
//           world.addTile(i, j, new FlameItem());
//           world.addTile(i, j, new Brick(true));
//         } else if (r == 9) {
//           world.addTile(i, j, new BombItem());
//           world.addTile(i, j, new Brick(true));
//         } else if (r == 10) {
//           world.addTile(i, j, new SpeedItem());
//           world.addTile(i, j, new Brick(true));
//         } else if (r < 20) {
//           world.addTile(i, j, new Brick());
//         }
//       }
//     }

    // Random rand = new Random();

    // int mapWidth = 21; // map width in tiles
    // int mapHeight = 15; // map height in tiles
    // this.world = new World(mapWidth, mapHeight);
    // EntityController<? super Player> playerController = new KeyBoardPlayerController(inputHandlers);
    // this.world.setPlayer(new Player(1, 1, playerController));
    // EntityController<? super Enemy> balloomController = RandomMoveController.INSTANCE;
    // this.world.addEnemy(new Balloom(3, 3, balloomController));
    // EntityController<? super Enemy> broomController = RandomMoveController.INSTANCE;
    // this.world.addEnemy(new Broom(5, 7, broomController));
    // EntityController<? super Enemy> bearController = RandomMoveController.INSTANCE;
    // for (int i = 0; i < 10; ++i) {
    //   this.world.addEnemy(new Bear(rand.nextInt(mapWidth) + 1, rand.nextInt(mapHeight) + 1, bearController));
    // }
    // EntityController<? super Enemy> onealController = new KeyboardEnemyController(inputHandlers);
    // this.world.addEnemy(new Oneal(7, 3, onealController));

    // for (int i = 1; i <= mapWidth; ++i) {
    //   for (int j = 1; j <= mapHeight; ++j) {
    //     if (i + j <= 3) {
    //       continue;
    //     }
    //     if (i % 2 == 0 && j % 2 == 0) {
    //       world.addTile(i, j, new Wall());
    //       continue;
    //     }
    //     int r = rand.nextInt(80);
    //     if (r == 8) {
    //       world.addTile(i, j, new FlameItem());
    //       world.addTile(i, j, new Brick(true));
    //     } else if (r == 9) {
    //       world.addTile(i, j, new BombItem());
    //       world.addTile(i, j, new Brick(true));
    //     } else if (r == 10) {
    //       world.addTile(i, j, new SpeedItem());
    //       world.addTile(i, j, new Brick(true));
    //     } else if (r < 20) {
    //       world.addTile(i, j, new Brick());
    //     }
    //   }
    // }
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

  public void setPoint(Pane root_, Text point, Text timer, Text lives, Text namePlayer) {
    Image dashboard = ResourceManager.dashboard;
    ImageView dashboardView = new ImageView(dashboard);
    dashboardView.setX(0);
    dashboardView.setY(0);
    dashboardView.setFitHeight(48);
    dashboardView.setFitWidth(48 * 21);

    point.setX(48 * 5-30);
    point.setY(29);

    timer.setX(48 * 9 + 17);
    timer.setY(29);

    lives.setX(48 * 12 + 35);
    lives.setY(29);

    namePlayer.setX(48 * 16);
    namePlayer.setY(29);

    lives.setFont(Font.loadFont("file:target/classes/font/Minecrafter_Alt.ttf", 18));
    lives.setFill(Color.YELLOW);
    point.setFont(Font.loadFont("file:target/classes/font/Minecrafter_Alt.ttf", 18));
    point.setFill(Color.YELLOW);
    timer.setFont(Font.loadFont("file:target/classes/font/Minecrafter_Alt.ttf", 18));
    timer.setFill(Color.YELLOW);
    namePlayer.setFont(Font.loadFont("file:target/classes/font/Minecrafter_Alt.ttf", 18));
    namePlayer.setFill(Color.YELLOW);
    root_.getChildren().addAll(dashboardView, point, timer, lives, namePlayer);
  }

  public Iterable<EventHandler<KeyEvent>> getInputHandlers() {
    return this.inputHandlers;
  }

  public void start() {
    this.gameLoop.start();
  }

  public void stop() {
    this.gameLoop.stop();
  }

  public void reload() {
    this.inputHandlers.clear();
    loadMap(currentLevel = 1);
    this.inputHandlers.add(new KeyboardLevelController());
  }

  // this function should be called when the instance is not used anymore.
  public void close() {
    this.gameLoop.stop();
    this.inputHandlers.clear();
  }

  public void prevLevel() {
    if (this.currentLevel > 1) {
      this.loadMap(--this.currentLevel);
    }
  }

  public void nextLevel() {
    if (this.currentLevel == ResourceManager.levels.length) {
      Bomberman.closeApp();
    } else {
      this.loadMap(++this.currentLevel);
      level_played++;
      this.world.getPlayer().setPoint(init_point + level_played * 1000);
    }
  }

  public void loadMap(int level) {
    loadMap(ResourceManager.levels[level - 1]);
  }

  public void loadMap(MapData mapData) {
    this.inputHandlers.removeIf((handler) -> handler instanceof EntityController);
    this.world = new World(mapData.getWidth(), mapData.getHeight());
    for (int i = 1; i <= world.getMapWidth(); ++i) {
      for (int j = 1; j <= world.getMapHeight(); ++j) {
        switch (mapData.getMap()[i][j]) {
          case '#':
            this.world.addTile(i, j, new Wall());
            break;
          case '*':
            this.world.addTile(i, j, new Brick());
            break;
          case 'x':
            this.world.addTile(i, j, new Brick(new Portal()));
            break;
          case 'f':
            this.world.addTile(i, j, new Brick(new FlameItem()));
            break;
          case 'b':
            this.world.addTile(i, j, new Brick(new BombItem()));
            break;
          case 's':
            this.world.addTile(i, j, new Brick(new SpeedItem()));
            break;
          case 'p':
            this.world.setPlayer(new Player(
                    i, j, new KeyBoardPlayerController(this.inputHandlers)));
            this.world.getPlayer().setLives(3);
            break;
          case 't':
            this.world.addTile(i, j, new Brick(new TimeItem()));
            break;
          case '1':
            this.world.addEnemy(new Balloom(i, j, AILowMoveController.INSTANCE));
            break;
          case '2':
            this.world.addEnemy(new Oneal(i, j, AIIntelligent.INSTANCE));
            break;
          case '3':
            this.world.addEnemy(new Broom(i, j, AILowMoveController.INSTANCE));
            break;
          case '4':
            this.world.addEnemy(new Bear(i, j, AILowMoveController.INSTANCE));
            break;
        }
      }
    }
    this.world.addTrigger(new BlinkBrickTrigger());
    this.world.addTrigger(new ActivatePortalTrigger());
    this.world.addTrigger(new PlayerEnterPortalTrigger());
    var respawnPlayer = new RespawnPlayer(1, 1);
    respawnPlayer.setLivesProperty(new PlayerLivesProperty(this.world.getPlayer()));
    this.world.addExtension(respawnPlayer);
  }

  void update(double dt) {
    world.update(dt);
    runTriggers();
    if (this.world.isGameOver()) {
      Bomberman.closeApp();
    }
    updateSetText(dt);
    adjustCanvasView();
  }

  void render() {
    GraphicsContext target = graphicsContext2D();
    Affine transform = graphicsContext2D().getTransform();
    graphicsContext2D().setTransform(new Affine());
    target.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    graphicsContext2D().setTransform(transform);
    world.renderTo(graphicsContext2D()); 
  }

  void updateSetText(double dt) {
    timesLeft -= dt;
    if (getWorld().getPlayer() != null) {
      this.playerLives = getWorld().getPlayer().getLives();
    }

    if (getWorld().getPlayer() != null) {
      if (getWorld().getPlayer().getTime()) {
        timesLeft += 60 * 60;
        getWorld().getPlayer().setIncreaseTime(false);
      }
    }

    if (timesLeft <= 0) {
      getWorld().setGameOver(true);

      //GAME OVER
      Bomberman.closeApp();
    }
    Player player_ = getWorld().getPlayer();
    if (player_ != null) {
      init_point = player_.getPoint();
    }
    point.setText("" + init_point);
    timer.setText("" + (timesLeft / 60) );
    lives.setText("" + this.playerLives);
    namePlayer.setText("Bomberman-N2");
  }

  private void runTriggers() {
    for (GameStateTrigger trigger : this.triggers.toArray(new GameStateTrigger[0])) {
      if (trigger.checkCondition(this)) {
        trigger.activate(this);
      }
    }
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
