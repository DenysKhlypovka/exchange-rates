package org.exchangerates;

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
      bankService.setBankType(bankType.name());
      return bankService.getCurrentRates();
    }).forEach(bankRatesDto -> System.out.println(bankRatesDto.getBankType().name() + ": " + bankRatesDto.getRates()));
  }

  @Test
  void allBanksShouldReturnRatesForDate() {
    LocalDate date = LocalDate.now().minusMonths(1);

    Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType.name());
      return bankService.getRatesForDate(date);
    }).forEach(bankRatesDto -> System.out.println(bankRatesDto.getBankType().name() + ": " + bankRatesDto.getRates()));
  }

  @Test
  void allBanksShouldReturnRatesForPeriod() {
    LocalDate dateFrom = LocalDate.now().minusMonths(1);
    LocalDate dateTo = LocalDate.now().minusWeeks(2);

    Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType.name());
      return bankService.getRatesForPeriod(dateFrom, dateTo);
    }).flatMap(Collection::stream).forEach(bankRatesDto -> System.out.println(bankRatesDto.getBankType().name() + ": " + bankRatesDto.getRates()));
  }
}
