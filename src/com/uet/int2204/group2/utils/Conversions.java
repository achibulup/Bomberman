package com.uet.int2204.group2.utils;

public class Conversions {
  // from nanoseconds to seconds.
  public static double nanostoSeconds(long nano) {
    return ((double) nano) / 1000000000;
  }

  // from seconds to nanoseconds.
  public static long secondsToNanos(double seconds) {
    return (long) (seconds * 1000000000);
  }
}
