package com.uet.int2204.group2.controller.Algorithm;

import com.uet.int2204.group2.World;
import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.entity.Enemy;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.utils.Direction;

import java.util.List;
import java.util.Random;

import static com.uet.int2204.group2.controller.Algorithm.BFS.shortestPath;

public class AIIntelligent implements EntityController<Enemy> {
    public static AIIntelligent INSTANCE = new AIIntelligent();
    protected Random rand = new Random();
    @Override
    public void control(Enemy enemy) {
        Player player = enemy.getWorld().getPlayer();
        enemy.setDirection(getDirectionHigh(getDirection(player, enemy)));
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

    public int getDirection(Player player, Enemy enemy) {
        char[][] matrix = enemy.getWorld().getMatrix();

        if (player == null) {
            return rand.nextInt(4);
        }
        List<Point> path = shortestPath(matrix, new Point(player.getTileY(), player.getTileX()),
                                                new Point(enemy.getTileY(), enemy.getTileX()));
        if (path == null) {
            return rand.nextInt(4);
        } else if (path.size() == 1) {
            return -1;
        } else {
            return calculateDirection(path.get(0), path.get(1));
        }
    }

    protected int calculateDirection(Point start, Point end) {
        if (end.col > start.col) return 0;
        if (end.col < start.col) return 1;
        if (end.row < start.row) return 2;
        if (end.row > start.row) return 3;

        return -1;
    }
}
