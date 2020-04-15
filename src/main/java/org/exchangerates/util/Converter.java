package org.exchangerates.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Converter {
  public static BigDecimal stringToBigDecimal(String inputNumber) {
    try {
      return new BigDecimal(inputNumber.replaceAll(",", "."));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Date stringToDate(String inputValue) {
    return stringToDate(inputValue, DatePattern.DD_MM_YYYY_DOT);
  }

  public static Date stringToDate(String inputValue, DatePattern datePattern) {
    try {
      DateFormat format = new SimpleDateFormat(datePattern.getPattern(), Locale.ENGLISH);
      return format.parse(inputValue);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public enum DatePattern {
    DD_MM_YYYY_DOT("dd.MM.yyyy"), YYYY_MM_DD_DASH("yyyy-MM-dd");
    private String pattern;

    DatePattern(String pattern) {
      this.pattern = pattern;
    }

    public String getPattern() {
      return pattern;
    }
  }
}
