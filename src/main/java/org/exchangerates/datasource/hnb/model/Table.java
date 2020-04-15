package org.exchangerates.datasource.hnb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement(name = "tecajna_lista")
public class Table {
  @XmlElement(name = "item")
  private ArrayList<Rate> rateList;

  public ArrayList<Rate> getRateList() {
    return rateList;
  }

  public Date getDate() {
    return rateList.stream().findAny().map(Rate::getDate).orElse(null);
  }
}
