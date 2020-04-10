package org.exchangerates.datasource.nbp;

import org.exchangerates.service.AbstractBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NbpService implements AbstractBankService {
  String BASE_URL = "http://api.nbp.pl/api/exchangerates/tables/";

  @Autowired
  private NbpParser nbpParser;

  @Override
  public String getData() {
    return getType() + " data";
  }

  @Override
  public String getType() {
    return "nbp";
  }
}
