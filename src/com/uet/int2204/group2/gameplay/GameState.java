package com.uet.int2204.group2.gameplay;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;

import com.uet.int2204.group2.Bomberman;
import com.uet.int2204.group2.controller.AIHighMoveController;
import com.uet.int2204.group2.controller.AILowMoveController;
import com.uet.int2204.group2.controller.AIMediumMoveController;
import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.controller.KeyBoardPlayerController;
import com.uet.int2204.group2.controller.algorithm.AIIntelligent;
import com.uet.int2204.group2.entity.movable.Balloom;
import com.uet.int2204.group2.entity.movable.Bear;
import com.uet.int2204.group2.entity.movable.Broom;
import com.uet.int2204.group2.entity.movable.Fire;
import com.uet.int2204.group2.entity.movable.Frog;
import com.uet.int2204.group2.entity.movable.Oneal;
import com.uet.int2204.group2.entity.movable.Player;
import com.uet.int2204.group2.entity.tile.Brick;
import com.uet.int2204.group2.entity.tile.Portal;
import com.uet.int2204.group2.entity.tile.Wall;
import com.uet.int2204.group2.entity.tile.item.BombItem;
import com.uet.int2204.group2.entity.tile.item.DetonatorItem;
import com.uet.int2204.group2.entity.tile.item.FlameItem;
import com.uet.int2204.group2.entity.tile.item.LifeItem;
import com.uet.int2204.group2.entity.tile.item.PiercingFlameItem;
import com.uet.int2204.group2.entity.tile.item.SpeedItem;
import com.uet.int2204.group2.entity.tile.item.TimeItem;
import com.uet.int2204.group2.gameplay.trigger.GameStateTrigger;
import com.uet.int2204.group2.gameplay.trigger.KeyboardLevelController;
import com.uet.int2204.group2.gameplay.trigger.NextLevelTrigger;
import com.uet.int2204.group2.map.ActivatePortalTrigger;
import com.uet.int2204.group2.map.BlinkBrickTrigger;
import com.uet.int2204.group2.map.MapData;
import com.uet.int2204.group2.map.PlayerEnterPortalTrigger;
import com.uet.int2204.group2.map.RespawnPlayer;
import com.uet.int2204.group2.map.World;
import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.Maths;
import com.uet.int2204.group2.utils.ResourceManager;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

public class GameState extends Pane implements Closeable {
  public static int CANVAS_WIDTH = Bomberman.WIDTH;
  public static int CANVAS_HEIGHT = Bomberman.HEIGHT;

  public static final int PLAYER_LIVES = 4;

  private World world;
  private Canvas canvas;
  private AnimationTimer gameLoop;

  private Collection<EventHandler<KeyEvent>> inputHandlers = new ArrayList<>();
  private Collection<GameStateTrigger> triggers = new ArrayList<>();

  private Runnable gameOver;

  public Text point = new Text();
  public Text timer = new Text();
  public int timesLeft = 180 * 60;
  public int init_point = 0;
  public int level_played = 0;
  public Text lives = new Text();
  public Text namePlayer = new Text();

  int currentLevel;
  int playerLives;

  public GameState() {
    this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    this.canvas.setTranslateX(0);
    this.canvas.setTranslateY(48 + 8);
    getChildren().add(this.canvas);
    setPoint(point, timer, lives, namePlayer);

    this.playerLives = PLAYER_LIVES;
    loadMap(currentLevel = 1);
    KeyboardLevelController levelController = new KeyboardLevelController();
    this.inputHandlers.add(levelController);
    this.triggers.add(levelController);
    this.triggers.add(new NextLevelTrigger());
    this.gameLoop = new GameLoop(this);
  }

  public World getWorld() {
    return this.world;
  }

  public GraphicsContext graphicsContext2D() {
    return this.canvas.getGraphicsContext2D();
  }

  public Parent getRoot() {
    return this;
  }

  public void setGameOver(Runnable gameOver) {
    this.gameOver = gameOver;
  }

  public void setPoint(Text point, Text timer, Text lives, Text namePlayer) {
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
    getChildren().addAll(dashboardView, point, timer, lives, namePlayer);
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

  @Override
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
      timesLeft = 180 * 60;
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
          case 'l':
            this.world.addTile(i, j, new Brick(new LifeItem()));
            break;
          case 'd':
            this.world.addTile(i, j, new Brick(new DetonatorItem()));
            break;
          case 'w':
            this.world.addTile(i, j, new Brick(new PiercingFlameItem()));
            break;
          case 't':
            this.world.addTile(i, j, new Brick(new TimeItem()));
            break;
          case 'p':
            this.world.setPlayer(new Player(
                    i, j, new KeyBoardPlayerController(this.inputHandlers)));
            this.world.getPlayer().setLives(3);
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
            this.world.addEnemy(new Bear(i, j, AIHighMoveController.INSTANCE));
            break;
          case '5':
            this.world.addEnemy(new Frog(i, j, AIMediumMoveController.INSTANCE));
            break;
          case '6':
            this.world.addEnemy(new Fire(i, j, AILowMoveController.INSTANCE));
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
      this.gameOver.run();
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
