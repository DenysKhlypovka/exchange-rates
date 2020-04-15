package org.exchangerates.datasource.hnb.model;

import org.exchangerates.util.Converter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Rate {
  @XmlElement(name="drzava_iso")
  private String currency;
  @XmlElement(name="jedinica")
  private BigDecimal divider;
  @XmlElement(name="srednji_tecaj")
  private String rate;
  @XmlAttribute(name = "datum_primjene")
  private String date;

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getRate() {
    return Converter.stringToBigDecimal(rate).divide(divider, 5, RoundingMode.HALF_UP);
  }

  public Date getDate() {
    return Converter.stringToDate(date, Converter.DatePattern.YYYY_MM_DD_DASH);  }
}
