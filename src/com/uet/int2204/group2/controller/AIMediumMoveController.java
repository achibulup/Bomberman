package com.uet.int2204.group2.controller;

import java.util.Random;

import com.uet.int2204.group2.entity.movable.Enemy;
import com.uet.int2204.group2.entity.movable.Player;
import com.uet.int2204.group2.utils.Direction;

public class AIMediumMoveController implements EntityController<Enemy> {

    public static final AIMediumMoveController INSTANCE = new AIMediumMoveController();
    private static final Random rand = new Random();

    @Override
    public void control(Enemy enemy) {
        enemy.setDirection(mediumRanDir(enemy.getWorld().getPlayer(), enemy));
    }

    private static Direction mediumRanDir(Player player, Enemy enemy) {
        int dir = -1;
        if (player == null) {
            dir = rand.nextInt(4);
        } else {
            int vertical = rand.nextInt(2);
            if (vertical == 1) {
                int v = calculateColDirection(player, enemy);
                if (v != -1) {
                    dir = v;
                } else {

                    dir = calculateRowDirection(player, enemy);
                }
            } else {
                int h = calculateRowDirection(player, enemy);
                if (h != -1) {
                    dir = h;
                } else {
                    dir = calculateColDirection(player, enemy);
                }
            }
        }

        switch (dir) {
            case -1:
                return Direction.NONE;
            case 0:
                return Direction.UP;
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.LEFT;
            case 3:
                return Direction.RIGHT;
        }
        throw new RuntimeException("Random.nextInt(4) return value out of range");
    }

    protected static int calculateColDirection(Player player, Enemy enemy) throws NullPointerException {
        if(player.getTileX() < enemy.getTileX()) {
            return 2;
        } else if(player.getTileX() > enemy.getTileX()) {
            return 3;
        }
        return -1;
    }

    protected static int calculateRowDirection(Player player, Enemy enemy) throws NullPointerException {
        if(player.getTileY() < enemy.getTileY()) {
            return 0;
        } else if(player.getTileY() > enemy.getTileY()) {
            return 1;
        }
        return -1;
    }


}
