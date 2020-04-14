package org.exchangerates.util;

import org.exchangerates.model.Currency;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Converter {
  public static String currencyToString(Currency currency) {
    return Objects.nonNull(currency) ? currency.name() : "";
  }

  public static Currency stringToCurrency(String currencyStr) throws Exception {
    try {
      return Currency.valueOf(currencyStr);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
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
    try {
      DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
      return format.parse(inputValue);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
