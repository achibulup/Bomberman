package com.uet.int2204.group2.controller;

import javafx.scene.input.KeyEvent;

public class Input {
  private KeyEvent keyEvent;
  private boolean pressed;
  
  public Input(KeyEvent event, boolean pressed) {
    this.keyEvent = event;
    this.pressed = pressed;
  }

  public KeyEvent getKeyEvent() {
    return keyEvent;
  }

  public boolean isPressed() {
    return pressed;
  }

  public void setKeyEvent(KeyEvent keyEvent) {
    this.keyEvent = keyEvent;
  }

  public void setPressed(boolean pressed) {
    this.pressed = pressed;
  }

}
