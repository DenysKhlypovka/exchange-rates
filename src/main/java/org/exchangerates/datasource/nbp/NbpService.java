package org.exchangerates.datasource.nbp;

import org.exchangerates.model.BankType;
import org.exchangerates.service.AbstractBankService;
import org.springframework.stereotype.Service;

@Service
public class NbpService extends AbstractBankService {

  public NbpService() {
    type = BankType.nbp;
    baseUrl = "http://api.nbp.pl/api/exchangerates/tables/";
    parser = new NbpParser();
  }

  @Override
  public String getData() {
    return getType().getCurrency()  + " data";
  }
}
