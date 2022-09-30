package com.uet.int2204.group2.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import com.uet.int2204.group2.entity.Entity;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public abstract class KeyBoardEntityController<T extends Entity> 
implements EntityController<T>, EventHandler<KeyEvent> {
  private Queue<KeyEvent> events = new LinkedList<>();

  public KeyBoardEntityController() {
  }

  // add this to the a collection
  public KeyBoardEntityController(Collection<EventHandler<KeyEvent>> handlerList) {
    this();
    handlerList.add(this);
  }

  @Override public void handle(KeyEvent event) {
    this.events.add(event);
  } 

  public KeyEvent nextInput() {
    return this.events.poll();
  }
}
