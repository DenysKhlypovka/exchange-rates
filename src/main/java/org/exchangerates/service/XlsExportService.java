package org.exchangerates.service;

import org.exchangerates.dto.BankRatesDto;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Service
public class XlsExportService {
  private final String CURRENCIES_TEMPLATE_PATH = "/static/xls/templates/rates-for-period.xlsx";
  private final String TEMP_FILE_PREFIX = "report";
  private final String TEMP_FILE_SUFFIX = ".xlsx";

  public void generateReport(List<BankRatesDto> bankRatesDtos) throws IOException {
    try (InputStream is = getTemplateInputStream()) {
      try (OutputStream os = new FileOutputStream(getTempFile())) {
        Context context = new Context();
        context.putVar("currencies", bankRatesDtos);
        JxlsHelper.getInstance().processTemplate(is, os, context);
      }
    }
  }

  private InputStream getTemplateInputStream() {
    return getClass().getResourceAsStream(CURRENCIES_TEMPLATE_PATH);
  }

  private File getTempFile() throws IOException {
    return Files.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX).toFile();
  }
}
