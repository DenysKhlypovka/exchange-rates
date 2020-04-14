package org.exchangerates.datasource.cnb.model;

import org.exchangerates.util.Converter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement(name = "kurzy")
public class Table {
  @XmlAttribute(name = "datum")
  private String date;
  @XmlElementWrapper(name = "tabulka")
  @XmlElement(name = "radek")
  private ArrayList<Rate> rateList;

  public Date getDate() {
    return Converter.stringToDate(date);
  }

  public ArrayList<Rate> getRateList() {
    return rateList;
  }
}
