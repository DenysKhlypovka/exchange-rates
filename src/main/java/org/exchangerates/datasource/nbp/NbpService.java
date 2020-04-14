package org.exchangerates.datasource.nbp;

import org.exchangerates.datasource.nbp.model.ArrayOfExchangeRatesTable;
import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

@Service
public class NbpService extends BankService {
  @PostConstruct
  public void init() {
    baseUrl = "http://api.nbp.pl/api/exchangerates/tables/";
    type = BankType.nbp;
  }

  @Override
  public BigDecimal test() {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(ArrayOfExchangeRatesTable.class);
      URL url = new URL(baseUrl + "a" + URL_XML_FORMAT);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      ArrayOfExchangeRatesTable exchangeRatesTable = (ArrayOfExchangeRatesTable) jaxbUnmarshaller.unmarshal(new InputSource(url.openStream()));
      return exchangeRatesTable.getExchangeRatesTable().getRateList().get(0).getRate();
    } catch (JAXBException | IOException e) {
      e.printStackTrace();
    }
    return BigDecimal.ZERO;
  }
}