package org.exchangerates;

import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.exchangerates.service.XlsExportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class XlsExportServiceTest {
  @Autowired
  BankService bankService;
  @Autowired
  XlsExportService xlsExportService;

  @Test
  void reportShouldBeGenerated() throws IOException {
    bankService.setBankType("NBU");
    xlsExportService.generateReportForBank(bankService.getRatesForPeriod(LocalDate.now().minusWeeks(2), LocalDate.now().minusWeeks(1)));
  }

  @Test
  void reportForMultipleBanksShouldBeGenerated() throws IOException {
    List<List<BankRatesDto>> bankRates = Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType.name());
      return bankService.getRatesForPeriod(LocalDate.now().minusDays(10), LocalDate.now().minusDays(6));
    }).collect(Collectors.toList());

    xlsExportService.generateReportForMultipleBanks(bankRates);
  }
}
