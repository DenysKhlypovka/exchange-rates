package org.exchangerates.xml.adapter;

import org.exchangerates.util.DateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAdapterDot extends XmlAdapter<String, LocalDate> {

  private static final ThreadLocal<DateTimeFormatter> dateFormat = new ThreadLocal<DateTimeFormatter>() {

    @Override
    protected DateTimeFormatter initialValue() {
      return DateTimeFormatter.ofPattern(DateFormat.DD_MM_YYYY_DOT.getPattern());
    }
  };

  @Override
  public LocalDate unmarshal(String inputDateStr) {
    return LocalDate.parse(inputDateStr, dateFormat.get());
  }

  @Override
  public String marshal(LocalDate inputDate) {
    return dateFormat.get().format(inputDate);
  }
}
