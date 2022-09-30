package com.uet.int2204.group2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.uet.int2204.group2.controller.KeyInputHandler;
import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.ResourceManager;

public class Bomberman extends Application {
  private static Scene scene;
  private static Set<KeyCode> pressedKeys = new HashSet<>();
  private static Iterable<KeyInputHandler> inputHandlers = Collections.emptyList();

  public static void main(String[] args) {
    ResourceManager.load();
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("menu"), Constants.TILE_SIZE * 12, Constants.TILE_SIZE * 12);
    scene.setOnKeyPressed((keyEvent) -> {
      if (!pressedKeys.contains(keyEvent.getCode())) {
        pressedKeys.add(keyEvent.getCode());
        for (var handler : inputHandlers) {
          handler.onKeyPressed(keyEvent);
        }
      }
    });
    scene.setOnKeyReleased((keyEvent) -> {
      pressedKeys.remove(keyEvent.getCode());
      for (var handler : inputHandlers) {
        handler.onKeyReleased(keyEvent);
      }
    });
    stage.setScene(scene);
    stage.show();
  }

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  public static void setRoot(Parent node) {
    scene.setRoot(node);
  }

  public static void setInputHandlers(Iterable<KeyInputHandler> handlers) {
    inputHandlers = handlers;
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Bomberman.class.getResource("/fxml/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }
}