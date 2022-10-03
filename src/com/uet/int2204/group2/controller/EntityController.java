package com.uet.int2204.group2.controller;

import com.uet.int2204.group2.entity.MovableEntity;

public interface EntityController<T extends MovableEntity> {
  static final EntityController<MovableEntity> doNothingController = (entt) -> {}; 

  void control(T entity);
}
