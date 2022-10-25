package com.uet.int2204.group2.controller.Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BFS {

    private static class Cell {
        int row;
        int col;
        int dist;
        Cell prev;

        Cell(int row, int col, int dist, Cell prev) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.prev = prev;
        }
    }

    // BFS, Time O(n^2), Space O(n^2)
    public static List<Point> findSortPath(Matrix matrix, Point start, Point end) {
        int sx = start.row, sy = start.col;
        int dx = end.row, dy = end.col;

        if (matrix.isBlocked(sx, sy))
            return null;

        int m = matrix.getWidth();
        int n = matrix.getHeight();
        Cell[][] cells = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!matrix.isBlocked(i, j)) {
                    cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null);
                }
            }
        }

        LinkedList<Cell> queue = new LinkedList<>();
        Cell src = cells[sx][sy];
        src.dist = 0;
        queue.add(src);
        Cell dest = null;
        Cell p;
        while ((p = queue.poll()) != null) {
            if (p.row == dx && p.col == dy) {
                dest = p;
                break;
            }

            // moving up
            visit(cells, queue, p.row - 1, p.col, p);

            // moving down
            visit(cells, queue, p.row + 1, p.col, p);

            // moving left
            visit(cells, queue, p.row, p.col - 1, p);

            //moving right
            visit(cells, queue, p.row, p.col + 1, p);
        }

        if (dest == null) {
            return null;
        } else {
            //LinkedList<Cell> path = new LinkedList<>();
            List<Point> path = new ArrayList<>();
            do {
                //System.out.println(p.x + " " + p.y);
                path.add(new Point(p.row, p.col));
            } while ((p = p.prev) != null);
            return path;
        }
    }

    static void visit(Cell[][] cells, LinkedList<Cell> queue, int x, int y, Cell parent) {
        if (x < 0 || x >= cells.length || y < 0
                || y >= cells[0].length || cells[x][y] == null) {
            return;
        }
        int dist = parent.dist + 1;
        Cell p = cells[x][y];
        if (dist < p.dist) {
            p.dist = dist;
            p.prev = parent;
            queue.add(p);
        }
    }

}
