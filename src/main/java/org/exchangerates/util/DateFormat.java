package org.exchangerates.util;

public enum DateFormat {
  DD_MM_YYYY_DOT("dd.MM.yyyy"), YYYY_MM_DD_DASH("yyyy-MM-dd");
  private String pattern;

  DateFormat(String pattern) {
    this.pattern = pattern;
  }

  public String getPattern() {
    return pattern;
  }
}
