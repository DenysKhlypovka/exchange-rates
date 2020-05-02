package org.exchangerates.util;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class TextUtil {
  public static String generateRandomPassword() {
    byte[] array = new byte[20];
    new Random().nextBytes(array);
    return new String(array, StandardCharsets.UTF_8);
  }

  public static BigDecimal stringToBigDecimal(String inputNumber) {
    try {
      return new BigDecimal(inputNumber.replaceAll(",", "."));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Date stringToDate(String inputValue) {
    return stringToDate(inputValue, DateFormat.DD_MM_YYYY_DOT);
  }

  public static Date stringToDate(String inputValue, DateFormat dateFormat) {
    try {
      java.text.DateFormat format = new SimpleDateFormat(dateFormat.getPattern(), Locale.ENGLISH);
      return format.parse(inputValue);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
