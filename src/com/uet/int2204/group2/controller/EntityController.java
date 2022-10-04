package com.uet.int2204.group2.controller;

import com.uet.int2204.group2.entity.Entity;

public interface EntityController<T extends Entity> {
  static final EntityController<Entity> doNothingController = (entt) -> {}; 

  void control(T entity);
}
