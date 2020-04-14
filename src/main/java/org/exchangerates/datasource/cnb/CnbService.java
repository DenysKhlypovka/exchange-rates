package org.exchangerates.datasource.cnb;

import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class CnbService extends BankService {

  @PostConstruct
  public void init() {
    baseUrl = "https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/";
    type = BankType.cnb;
  }

  @Override
  public BigDecimal test() {
    return BigDecimal.ZERO;
  }

}