package org.exchangerates;

import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
class BankServiceTest {
  @Autowired BankService bankService;

  @Test
  void allBanksShouldReturnData() {
    Stream.of(BankType.values()).map(bankType -> bankService.getDataFromBank(bankType.name())).forEach(bankRatesDto -> System.out.println(bankRatesDto.getBankType().name() + ": " + bankRatesDto.getRates()));
  }
}
