package org.exchangerates.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TextUtil {
  public static String generateRandomPassword() {
    byte[] array = new byte[20];
    new Random().nextBytes(array);
    return new String(array, StandardCharsets.UTF_8);
  }
}
