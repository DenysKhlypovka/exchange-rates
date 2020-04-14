package org.exchangerates.datasource.nbu;

import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class NbuService extends BankService {
  @PostConstruct
  public void init() {
    baseUrl = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";
    type = BankType.nbu;
  }

  @Override
  public BigDecimal test() {
    return BigDecimal.ZERO;
  }

}