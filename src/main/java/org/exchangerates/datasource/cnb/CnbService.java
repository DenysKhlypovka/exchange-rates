package org.exchangerates.datasource.cnb;

import org.exchangerates.datasource.cnb.model.Table;
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
public class CnbService extends BankService {

  @PostConstruct
  public void init() {
    baseUrl = "https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/";
    type = BankType.cnb;
  }

  @Override
  public BigDecimal test() {

    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(Table.class);
      URL url = new URL(baseUrl + "denni_kurz.xml");
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      Table table = (Table) jaxbUnmarshaller.unmarshal(new InputSource(url.openStream()));
      return table.getRateList().get(0).getRate();
    } catch (JAXBException | IOException e) {
      e.printStackTrace();
    }
    return BigDecimal.ZERO;
  }

}