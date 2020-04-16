package org.exchangerates.service;

import org.exchangerates.model.BankType;
import org.exchangerates.model.ExchangeRatesTable;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.math.BigDecimal;
import java.net.URL;

@Service
public class BankService {

  public static BigDecimal getDataFromBank(BankType type) {
    Class<? extends ExchangeRatesTable> classType = type.getTableClass();
    return classType.cast(parseFromUrl(type.getUrl(), classType)).getRates().get(0).getRate();
  }

  static <T> Object parseFromUrl(String Url, Class<T> parameterClass) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(parameterClass);
      URL url = new URL(Url);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      return parameterClass.cast(jaxbUnmarshaller.unmarshal(new InputSource(url.openStream())));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
