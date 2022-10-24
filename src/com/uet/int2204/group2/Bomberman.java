package com.uet.int2204.group2;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.uet.int2204.group2.Menu.GameMenu;
import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.ResourceManager;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Bomberman extends Application {
  private static Scene scene;
  private static Set<KeyCode> pressedKeys = new HashSet<>();
  private static Iterable<EventHandler<KeyEvent>> inputHandlers = Collections.emptyList();

  public static int WIDTH = Constants.TILE_SIZE * 13;
  public static int HEIGHT = Constants.TILE_SIZE * 13;

  public static void main(String[] args) {
    ResourceManager.load();
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    Pane root = new Pane();
    root.setPrefSize(WIDTH, HEIGHT);
    Image img = ResourceManager.background;
    GameMenu gameMenu = new GameMenu();
    ImageView imageView = new ImageView(img);
    root.getChildren().addAll(imageView, gameMenu);

    scene = new Scene(root);
    scene.setOnKeyPressed((keyEvent) -> {
      if (!pressedKeys.contains(keyEvent.getCode())) {
        pressedKeys.add(keyEvent.getCode());
        for (var handler : inputHandlers) {
          handler.handle(keyEvent);
        }
      }
    });
    scene.setOnKeyReleased((keyEvent) -> {
      pressedKeys.remove(keyEvent.getCode());
      for (var handler : inputHandlers) {
        handler.handle(keyEvent);
      }
    });

    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  public static void setRoot(Parent node) {
    scene.setRoot(node);
  }

  public static void closeApp() {
    ((Stage) scene.getWindow()).close();
  }

  public static void setInputHandlers(Iterable<EventHandler<KeyEvent>> handlers) {
    inputHandlers = handlers;
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Bomberman.class.getResource("/fxml/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }
}
