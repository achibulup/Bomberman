package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.entity.MovableEntity.Direction;
import com.uet.int2204.group2.utils.Conversions;
public interface MediumEnemy {
    Direction getDirection();

    boolean isMovable(Direction dir);

    void adjustedMove(double distance);

    boolean isAligned();

    double getSpeed();

    void control();

    public default void update(long dt) {
        if (isMovable(getDirection())) {
            adjustedMove(getSpeed() * Conversions.nanostoSeconds(dt));
        }
        if (isAligned()) {
            control();
        }
    }
}
