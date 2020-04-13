package org.exchangerates.datasource.cnb;

import org.exchangerates.model.BankType;
import org.exchangerates.service.AbstractBankService;
import org.springframework.stereotype.Service;

@Service
public class CnbService extends AbstractBankService {

  public CnbService() {
    type = BankType.cnb;
    baseUrl = "https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/";
    parser = new CnbParser();
  }

  @Override
  public String getData() {
    return getType().getCurrency() + " data3";
  }
}
