package org.exchangerates.xml.model.cnb;

import org.exchangerates.model.ExchangeRatesTable;
import org.exchangerates.xml.adapter.DateAdapterDot;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement(name = "kurzy")
public class CnbRatesTable implements ExchangeRatesTable {
  @XmlAttribute(name = "datum")
  @XmlJavaTypeAdapter(DateAdapterDot.class)
  private LocalDate date;

  @XmlElementWrapper(name = "tabulka")
  @XmlElement(name = "radek")
  private ArrayList<CnbRate> rateList;

  public LocalDate getDate() {
    return date;
  }

  public ArrayList<CnbRate> getRates() {
    return rateList;
  }
}
