package com.uet.int2204.group2.component;

import java.util.Random;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.entity.Brick;
import com.uet.int2204.group2.entity.Grass;
import com.uet.int2204.group2.entity.Wall;
import com.uet.int2204.group2.utils.Constants;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas {
  private World world;
  private Canvas canvas;
  private Group group;
  private AnimationTimer timer;

  public GameCanvas() {
    int width = Constants.TILE_SIZE * 12;
    int height = Constants.TILE_SIZE * 12;
    this.world = new World(10, 10);
    this.canvas = new Canvas(width, height);
    this.group = new Group();
    this.group.getChildren().add(this.canvas);
    group.setAutoSizeChildren(true);

    Random rand = new Random();
    for (int i = 1; i <= 10; ++i) {
      for (int j = 1; j <= 10; ++j) {
        int r = rand.nextInt(3);
        if (r == 0) {
          world.setTile(i, j, new Brick(i, j));
        } else if (r == 1) {
          world.setTile(i, j, new Wall(i, j));
        } else {
          world.setTile(i, j, new Grass(i, j));
        }
      }
    }
    world.setTile(1, 1, new Grass(1, 1));

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
    return this.group;
  }

  public void start() {
    this.timer.start();
  }

  public void stop() {
    this.timer.stop();
  }
}
