package com.uet.int2204.group2.controller;

import java.util.EventListener;

import javafx.scene.input.KeyEvent;

public interface KeyInputHandler extends EventListener {
  void onKeyPressed(KeyEvent event);

  void onKeyReleased(KeyEvent event);
}
