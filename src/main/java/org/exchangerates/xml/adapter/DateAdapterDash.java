package org.exchangerates.xml.adapter;

import org.exchangerates.util.DateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAdapterDash extends XmlAdapter<String, LocalDate> {

  private static final ThreadLocal<DateTimeFormatter> dateFormat = new ThreadLocal<DateTimeFormatter>() {

    @Override
    protected DateTimeFormatter initialValue() {
      return DateTimeFormatter.ofPattern(DateFormat.YYYY_MM_DD_DASH.getPattern());
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
