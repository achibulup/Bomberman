package com.uet.int2204.group2.controller.Algorithm;

public class EnemyMatrix implements Matrix {
  private char[][] matrix;

  public EnemyMatrix(char[][] matrix) {
    this.matrix = matrix;
  }

  @Override
  public int getWidth() {
    return this.matrix.length;
  }

  @Override
  public int getHeight() {
    return this.matrix[0].length;
  }

  @Override
  public boolean isBlocked(int x, int y) {
    return this.matrix[x][y] != ' ';
  }
}
