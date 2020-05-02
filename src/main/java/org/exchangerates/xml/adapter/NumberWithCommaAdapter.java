package org.exchangerates.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

import static org.exchangerates.util.TextUtil.stringToBigDecimal;

public class NumberWithCommaAdapter extends XmlAdapter<String, BigDecimal> {

  @Override
  public BigDecimal unmarshal(String v) {
    return stringToBigDecimal(v);
  }

  @Override
  public String marshal(BigDecimal v) {
    return v.toString();
  }
}
