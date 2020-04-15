package org.exchangerates.datasource.hnb;

import org.exchangerates.datasource.hnb.model.Table;
import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.exchangerates.service.XmlParseService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class HnbService extends BankService {
  @PostConstruct
  public void init() {
    baseUrl = "http://api.hnb.hr/tecajn/v2";
    type = BankType.hnb;
    xmlParseService = new XmlParseService<Table>();
  }

  @Override
  public BigDecimal test() {

    String url = baseUrl + URL_XML_FORMAT;
    return ((Table)xmlParseService.parseFromUrl(url, Table.class)).getRateList().get(0).getRate();
  }
}