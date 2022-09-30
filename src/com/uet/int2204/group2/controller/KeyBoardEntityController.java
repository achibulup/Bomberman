package com.uet.int2204.group2.controller;

import java.util.Collection;

import com.uet.int2204.group2.entity.Entity;

public abstract class KeyBoardEntityController<T extends Entity> 
implements EntityController<T>, KeyInputHandler {
  public KeyBoardEntityController() {
  }

  // add this to a collection
  public KeyBoardEntityController(Collection<KeyInputHandler> handlerList) {
    this();
    handlerList.add(this);
  }
}
