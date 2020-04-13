package org.exchangerates.datasource.nbu;

import org.exchangerates.model.BankType;
import org.exchangerates.service.AbstractBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NbuService extends AbstractBankService {

  public NbuService() {
    type = BankType.nbu;
    baseUrl = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";
    parser = new NbuParser();
  }

  @Override
  public String getData() {
    return getType().getCurrency()  + " data3";
  }
}
