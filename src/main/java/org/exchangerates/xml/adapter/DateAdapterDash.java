package org.exchangerates.xml.adapter;

import org.exchangerates.util.DateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapterDash extends XmlAdapter<String, Date> {

  private static final ThreadLocal<java.text.DateFormat> dateFormat = new ThreadLocal<java.text.DateFormat>() {

    @Override
    protected java.text.DateFormat initialValue() {
      return new SimpleDateFormat(DateFormat.YYYY_MM_DD_DASH.getPattern());
    }
  };

  @Override
  public Date unmarshal(String v) throws Exception {
    return dateFormat.get().parse(v);
  }

  @Override
  public String marshal(Date v) throws Exception {
    return dateFormat.get().format(v);
  }
}
