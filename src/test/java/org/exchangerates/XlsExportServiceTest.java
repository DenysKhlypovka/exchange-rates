package org.exchangerates;

import org.exchangerates.service.BankService;
import org.exchangerates.service.XlsExportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

@SpringBootTest
public class XlsExportServiceTest {
  @Autowired
  BankService bankService;
  @Autowired
  XlsExportService xlsExportService;

  @Test
  void reportShouldBeGenerated() throws IOException {
    bankService.setBankType("NBU");
    xlsExportService.generateReport(bankService.getRatesForPeriod(LocalDate.now().minusWeeks(2), LocalDate.now().minusWeeks(1)));
  }
}
