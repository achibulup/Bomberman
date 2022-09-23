package com.uet.int2204.group2.graphics;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Sprite {
  private Image source = null; // the source image
  private int x; // x position of the source rectangle;
  private int y; // y position of the source rectangle;
  private int width; // width of the source rectangle;
  private int height; // height of the source rectangle;

  public Sprite() {
  }

  public Sprite(Image image) {
    this(image, 0, 0, 
        (int) Math.floor(image.getWidth()), (int) Math.floor(image.getHeight()));
  }

  public Sprite(Image image, int x, int y, int width, int height) {
    setSourceImage(image);
    setX(x);
    setY(y);
    setWidth(width);
    setHeight(height);
  }

  // get the source image.
  public Image getSourceImage() {
    return this.source;
  }

  // set the source image.
  public void setSourceImage(Image image) {
    this.source = image;
  }

  // getter for x.
  public int getX() {
    return this.x;
  }

  // setter for x.
  public void setX(int x) {
    this.x = x;
  }

  // getter for y.
  public int getY() {
    return this.y;
  }

  // setter for y.
  public void setY(int y) {
    this.y = y;
  }

  // getter for width.
  public int getWidth() {
    return this.width;
  }

  // setter for width.
  public void setWidth(int width) {
    this.width = width;
  }
  
  // getter for height.
  public int getHeight() {
    return this.height;
  }

  // setter for height.
  public void setHeight(int height) {
    this.height = height;
  }

  /** 
   * draw this sprite to a canvas
   * @param target : the graphic context to draw to
   * @param destX : the x position of the destination rectangle
   * @param destY : the y position of the destination rectangle
   */
  public void drawTo(GraphicsContext target, int destX, int destY) {
    target.drawImage(getSourceImage(), 
        getX(), getY(), getWidth(), getHeight(),
        destX, destY, getWidth(), getHeight());
  }

  // create a sprite sheet from an image
  public static Sprite[] makeSpriteSheet(Image image, int spriteWidth, int spriteHeight) {
    int numOfSprites = (int) image.getWidth() / spriteWidth;
    Sprite[] result = new Sprite[numOfSprites];
    for (int i = 0; i < numOfSprites; ++i) {
      result[i] = new Sprite(image, i * spriteWidth, 0, spriteWidth, spriteHeight);
    }
    return result;
  }
}
