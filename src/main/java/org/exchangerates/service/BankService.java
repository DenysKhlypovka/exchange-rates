package org.exchangerates.service;

import org.exchangerates.datasource.cnb.model.Table;
import org.exchangerates.model.BankType;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.math.BigDecimal;
import java.net.URL;

public abstract class BankService<T> {
  protected String baseUrl;
  protected BankType type;
  protected String URL_XML_FORMAT = "?format=xml";

  public BankType getType() {
    return type;
  }

  public abstract BigDecimal test();

//  public T unmarshall(String URL) {
//    JAXBContext jaxbContext = JAXBContext.newInstance(T.class);
//    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//    return (T) jaxbUnmarshaller.unmarshal(new InputSource(new URL(URL).openStream()));
//  }
}
