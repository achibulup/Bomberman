package com.uet.int2204.group2.controller.Algorithm;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.entity.Enemy;
import com.uet.int2204.group2.entity.Player;
import com.uet.int2204.group2.utils.Direction;

import java.util.List;
import java.util.Random;

import static com.uet.int2204.group2.controller.Algorithm.BFS.findSortPath;

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

    public int getDirection(Player player, Enemy enemy) {
        if (player == null) {
            return rand.nextInt(4);
        }
        List<Point> path = findSortPath(
            new EnemyMatrix(enemy), new Point(player.getTileX(), player.getTileY()),
            new Point(enemy.getTileX(), enemy.getTileY()));
        if (path == null) {
            return rand.nextInt(4);
        } else if (path.size() == 1) {
            return -1;
        } else {
            return calculateDirection(path.get(0), path.get(1));
        }
    }

    protected int calculateDirection(Point start, Point end) {
        if (end.col < start.col) return 0;
        if (end.col > start.col) return 1;
        if (end.row < start.row) return 2;
        if (end.row > start.row) return 3;

        return -1;
    }
}
