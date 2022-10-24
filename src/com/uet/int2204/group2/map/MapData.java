package com.uet.int2204.group2.map;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapData {
  private int width;
  private int height;
  private char[][] map;


  public MapData() {
    setSize(0, 0);
  }

  public MapData(MapData copy) {
    setSize(copy.width, copy.height);
    for (int i = 1; i <= this.width; ++i) {
      for (int j = 1; j <= this.height; ++j) {
        this.map[i][j] = copy.map[i][j];
      }
    }
  }

  public MapData(InputStream inp) throws IOException {
    load(inp);
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
    this.map = new char[width + 2][height + 2];
    for (int i = 0; i < width + 2; ++i) {
      this.map[i][0] = '%';
      this.map[i][height + 1] = '%';
    }
    for (int i = 0; i < height + 2; ++i) {
      this.map[0][i] = '%';
      this.map[width + 1][i] = '%';
    }
  }

  public char[][] getMap() {
    return this.map;
  }

  public void load(String file) throws IOException {
    load(new FileInputStream(file));
  }

  public void load(InputStream inp) throws IOException {
    InputStreamReader inputReader = new InputStreamReader(inp);
    BufferedReader buffReader = new BufferedReader(inputReader);
    String firstLine = buffReader.readLine();
    String[] tokens = firstLine.split(" ");
    int width = Integer.parseInt(tokens[2]) - 2;
    int height = Integer.parseInt(tokens[1]) - 2;
    setSize(width, height);
    buffReader.readLine(); // skip the top edge
    for (int i = 1; i <= height; ++i) {
      String line = buffReader.readLine();
      for (int j = 1; j <= width; ++j) {
        this.map[j][i] = line.charAt(j);
      }
    }
    buffReader.readLine(); // skip the bottom edge
    inputReader.close();
    buffReader.close();
  }

}
