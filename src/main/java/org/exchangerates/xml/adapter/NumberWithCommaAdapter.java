package org.exchangerates.xml.adapter;

import org.exchangerates.util.Converter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

public class NumberWithCommaAdapter extends XmlAdapter<String, BigDecimal> {

  @Override
  public BigDecimal unmarshal(String v) throws Exception {
    return Converter.stringToBigDecimal(v);
  }

  @Override
  public String marshal(BigDecimal v) throws Exception {
    return v.toString();
  }
}
