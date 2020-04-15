package org.exchangerates.datasource.nbp;

import org.exchangerates.datasource.nbp.model.ArrayOfExchangeRatesTable;
import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.exchangerates.service.XmlParseService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class NbpService extends BankService {
  @PostConstruct
  public void init() {
    baseUrl = "http://api.nbp.pl/api/exchangerates/tables/";
    type = BankType.nbp;
    xmlParseService = new XmlParseService<ArrayOfExchangeRatesTable>();
  }

  @Override
  public BigDecimal test() {
    String url = baseUrl + "a" + URL_XML_FORMAT;
    return ((ArrayOfExchangeRatesTable)xmlParseService.parseFromUrl(url, ArrayOfExchangeRatesTable.class)).getExchangeRatesTable().getRateList().get(0).getRate();
  }
}