package org.exchangerates.datasource.cnb;

import org.exchangerates.datasource.cnb.model.Table;
import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.exchangerates.service.XmlParseService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class CnbService extends BankService {

  @PostConstruct
  public void init() {
    baseUrl = "https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/";
    type = BankType.cnb;
    xmlParseService = new XmlParseService<Table>();
  }

  @Override
  public BigDecimal test() {
    String url = baseUrl + "denni_kurz.xml";
    return ((Table)xmlParseService.parseFromUrl(url, Table.class)).getRateList().get(0).getRate();
  }
}