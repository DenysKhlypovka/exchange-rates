package org.exchangerates;

import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Stream;

@SpringBootTest
class BankServiceTest {
  @Autowired BankService bankService;

  @Test
  void allBanksShouldReturnCurrentRates() {
    Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType);
      BankRatesDto rates = bankService.getRatesForDate(null);
      System.out.print(rates.getDate() + "\t");
      return rates;
    }).forEach(bankRatesDto -> System.out.println(bankRatesDto.getBankType().name() + ": " + bankRatesDto.getRates()));
  }

  @Test
  void allBanksShouldReturnRatesForDate() {
    LocalDate date = LocalDate.now().minusMonths(1);

    Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType);
      return bankService.getRatesForDate(date);
    }).forEach(bankRatesDto -> System.out.println(bankRatesDto.getBankType().name() + ": " + bankRatesDto.getRates()));
  }

  @Test
  void allBanksShouldReturnRatesForPeriod() {
    LocalDate dateFrom = LocalDate.now().minusMonths(1);
    LocalDate dateTo = LocalDate.now().minusWeeks(2);

    Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType);
      return bankService.getRatesForPeriod(dateFrom, dateTo);
    }).flatMap(Collection::stream).forEach(bankRatesDto -> System.out.println(bankRatesDto.getBankType().name() + ": " + bankRatesDto.getRates()));
  }
}
