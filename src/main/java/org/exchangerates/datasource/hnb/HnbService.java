package org.exchangerates.datasource.hnb;

import org.exchangerates.model.BankType;
import org.exchangerates.service.AbstractBankService;
import org.springframework.stereotype.Service;

@Service
public class HnbService extends AbstractBankService {

  public HnbService() {
    type = BankType.hnb;
    baseUrl = "http://api.hnb.hr/tecajn/v2/";
    parser = new HnbParser();
  }

  @Override
  public String getData() {
    return getType().getCurrency() + " data2";
  }
}
