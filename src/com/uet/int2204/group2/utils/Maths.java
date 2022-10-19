package com.uet.int2204.group2.utils;

public class Maths {

  // see the definition online or the function body for more details.
  public static double clamp(double val, double low, double high) {
    return Math.min(Math.max(val, low), high);
  }
}
