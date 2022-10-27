package com.uet.int2204.group2.controller;

import com.uet.int2204.group2.entity.movable.Enemy;
import com.uet.int2204.group2.entity.movable.Player;
import com.uet.int2204.group2.utils.Direction;

import java.util.Random;

public class AIHighMoveController implements EntityController<Enemy> {
    public static final AIHighMoveController INSTANCE = new AIHighMoveController();
    private static final Random rand = new Random();

    @Override
    public void control(Enemy enemy) {
        Player player = (Player) enemy.getWorld().getPlayer();

        enemy.setDirection(getDirectionHigh(aiHighrandomDir(player, enemy)));

        int dir = aiHighrandomDir(player, enemy);
        if (getDirectionHigh(dir) == Direction.LEFT) {
            if (!enemy.isMovable(getDirectionHigh(dir))) {
                dir = 0;
                enemy.setDirection(getDirectionHigh(dir));
                if (!enemy.isMovable(getDirectionHigh(dir))) {
                    dir = 1;
                    enemy.setDirection(getDirectionHigh(dir));
                    if (!enemy.isMovable(getDirectionHigh(dir))) {
                        dir = 3;
                        enemy.setDirection(getDirectionHigh(dir));
                    }
                }
            } else {

            }
        } else if (getDirectionHigh(dir) == Direction.RIGHT) {
            if (!enemy.isMovable(getDirectionHigh(dir))) {
                dir = 0;
                enemy.setDirection(getDirectionHigh(dir));
                if (!enemy.isMovable(getDirectionHigh(dir))) {
                    dir = 2;
                    enemy.setDirection(getDirectionHigh(dir));
                    if (!enemy.isMovable(getDirectionHigh(dir))) {
                        dir = 1;
                        enemy.setDirection(getDirectionHigh(dir));
                    }
                }
            }
        } else if (getDirectionHigh(dir) == Direction.UP) {
            if (!enemy.isMovable(getDirectionHigh(dir))) {
                dir = 2;
                enemy.setDirection(getDirectionHigh(dir));
                if (!enemy.isMovable(getDirectionHigh(dir))) {
                    dir = 3;
                    enemy.setDirection(getDirectionHigh(dir));
                    if (!enemy.isMovable(getDirectionHigh(dir))) {
                        dir = 1;
                        enemy.setDirection(getDirectionHigh(dir));
                    }
                }
            }
        } else if (getDirectionHigh(dir) == Direction.DOWN) {
            if (!enemy.isMovable(getDirectionHigh(dir))) {
                dir = 2;
                enemy.setDirection(getDirectionHigh(dir));
                if (!enemy.isMovable(getDirectionHigh(dir))) {
                    dir = 3;
                    enemy.setDirection(getDirectionHigh(dir));
                    if (!enemy.isMovable(getDirectionHigh(dir))) {
                        dir = 0;
                        enemy.setDirection(getDirectionHigh(dir));
                    }
                }

            }
        }
    }

    private static int aiHighrandomDir(Player player, Enemy enemy) {
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

        return dir;
    }

    public static Direction getDirectionHigh(int dir) {
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
        if (player.getTileX() < enemy.getTileX()) {
            return 2;
        } else if (player.getTileX() > enemy.getTileX()) {
            return 3;
        }
        return -1;
    }

    protected static int calculateRowDirection(Player player, Enemy enemy) throws NullPointerException {
        if (player.getTileY() < enemy.getTileY()) {
            return 0;
        } else if (player.getTileY() > enemy.getTileY()) {

            return 1;
        }
        return -1;
    }
}
