package com.uet.int2204.group2.controller;

import com.uet.int2204.group2.entity.Enemy;
import com.uet.int2204.group2.entity.MovableEntity;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.utils.Direction;

import java.util.List;
import java.util.Random;

public class AIMoveController implements EntityController<MovableEntity>{

    public static final AIMoveController INSTANCE = new AIMoveController();
    private static final Random rand = new Random();

    @Override
    public void control(MovableEntity entity) {
        Player player = (Player) entity.getWorld().getPlayer();
        List<Enemy> enemies = (List<Enemy>) entity.getWorld().getEnemies();
        Enemy enemy = enemies.get(0);
        entity.setDirection(mediumRanDir(player, enemy));
    }

    private static Direction mediumRanDir(Player player, Enemy enemy) {
        int dir = -1;
        if (player == null) {
            System.out.println("Player null");
            dir = rand.nextInt(4);

        } else {
            System.out.println("player is not null");
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
                System.out.println("NONE");
                return Direction.NONE;
            case 0:
                System.out.println("UP");
                return Direction.UP;
            case 1:
                System.out.println("DOWN");
                return Direction.DOWN;
            case 2:
                System.out.println("LEFT");
                return Direction.LEFT;
            case 3:
                System.out.println("RIGHT");
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
