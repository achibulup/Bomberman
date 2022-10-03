package com.uet.int2204.group2.controller;

import java.util.Collection;

import com.uet.int2204.group2.entity.MovableEntity;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public abstract class KeyBoardEntityController<T extends MovableEntity> 
implements EntityController<T>, EventHandler<KeyEvent> {
  public KeyBoardEntityController() {
  }

  // add this to a collection
  public KeyBoardEntityController(Collection<EventHandler<KeyEvent>> handlerList) {
    this();
    handlerList.add(this);
  }
}
