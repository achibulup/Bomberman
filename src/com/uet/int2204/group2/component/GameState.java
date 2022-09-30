package com.uet.int2204.group2.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.controller.KeyBoardPlayerController;
import com.uet.int2204.group2.controller.KeyInputHandler;
import com.uet.int2204.group2.entity.Brick;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.entity.Wall;
import com.uet.int2204.group2.utils.Constants;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameState {
  private World world;
  private Canvas canvas;
  private Group root;
  private AnimationTimer timer;
  private Collection<KeyInputHandler> inputHandlers = new ArrayList<>();

  public GameState() {
    int width = Constants.TILE_SIZE * 12;
    int height = Constants.TILE_SIZE * 12;
    this.world = new World(10, 10);
    this.canvas = new Canvas(width, height);
    this.root = new Group();
    this.root.getChildren().add(this.canvas);
    this.root.setAutoSizeChildren(true);
    EntityController<? super Player> playerController = new KeyBoardPlayerController(inputHandlers);
    this.world.getPlayer().setController(playerController);

    Random rand = new Random();
    for (int i = 1; i <= 10; ++i) {
      for (int j = 1; j <= 10; ++j) {
        if (i == 1 && j == 1) {
          continue;
        }
        int r = rand.nextInt(3);
        if (r == 0) {
          world.addTile(i, j, Brick.class);
        } else if (r == 1) {
          world.addTile(i, j, Wall.class);
        }
      }
    }

    this.timer = new AnimationTimer() {
      long lastTime = -1;
      World world = getWorld();
      GraphicsContext target = graphicsContext2D();
      @Override public void handle(long now) {
        if (lastTime == -1) {
          world.update(0);
        }
        else {
          world.update(now - lastTime);
        }
        lastTime = now;
        world.renderTo(target);
      }
    };
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

  public Iterable<KeyInputHandler> getInputHandlers() {
    return this.inputHandlers;
  }

  public void start() {
    this.timer.start();
  }

  public void stop() {
    this.timer.stop();
  }
}
