package org.exchangerates;

import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {

  @InjectMocks
  BankService bankService;

  static Predicate<BankRatesDto> bankTypeIsNullPredicate = bankRatesDto -> bankRatesDto.getBankType() == null;

  @Test
  public void allBanksShouldReturnCurrentRates() {
    Assertions.assertFalse(Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType);
      return bankService.getRatesForDate();
    }).anyMatch(bankTypeIsNullPredicate));
  }

  @Test
  public void allBanksShouldReturnRatesForDate() {
    LocalDate date = LocalDate.now().minusMonths(1);

    Assertions.assertFalse(Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType);
      return bankService.getRatesForDate(date);
    }).anyMatch(bankTypeIsNullPredicate));
  }

  @Test
  public void allBanksShouldReturnRatesForPeriod() {
    LocalDate dateFrom = LocalDate.now().minusMonths(1);
    LocalDate dateTo = LocalDate.now().minusWeeks(2);

    Assertions.assertFalse(Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType);
      return bankService.getRatesForPeriod(dateFrom, dateTo);
    }).flatMap(Collection::stream).anyMatch(bankTypeIsNullPredicate));
  }
}
