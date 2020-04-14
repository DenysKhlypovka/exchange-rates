package org.exchangerates.datasource.hnb;

import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class HnbService extends BankService {
  @PostConstruct
  public void init() {
    baseUrl = "http://api.hnb.hr/tecajn/v2/";
    type = BankType.hnb;
  }

  @Override
  public BigDecimal test() {
    return BigDecimal.ZERO;
  }
}