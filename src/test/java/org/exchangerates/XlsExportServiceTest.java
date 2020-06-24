package org.exchangerates;

import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.exchangerates.service.XlsExportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class XlsExportServiceTest {
  @InjectMocks
  BankService bankService;
  @InjectMocks
  XlsExportService xlsExportService;

  @Test
  public void reportShouldBeGenerated() throws IOException {
    bankService.setBankType(BankType.NBU);
    xlsExportService.generateReportForBank(bankService.getRatesForPeriod(LocalDate.now().minusWeeks(2), LocalDate.now().minusWeeks(1)));
  }

  @Test
  public void reportForMultipleBanksShouldBeGenerated() throws IOException {
    List<List<BankRatesDto>> bankRates = Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType);
      return bankService.getRatesForPeriod(LocalDate.now().minusDays(10), LocalDate.now().minusDays(6));
    }).collect(Collectors.toList());

    xlsExportService.generateReportForMultipleBanks(bankRates);
  }
}
