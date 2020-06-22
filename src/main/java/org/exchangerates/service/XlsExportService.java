package org.exchangerates.service;

import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.model.BankType;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class XlsExportService {
  private final String XML_TEMPLATES_PATH = "/static/xls/templates";
  private final String SINGLE_BANK_RATES_TEMPLATE_PATH = XML_TEMPLATES_PATH + "/rates.xlsx";
  private final String MULTIPLE_BANKS_RATES_TEMPLATE_PATH = XML_TEMPLATES_PATH + "/rates-multiple-banks.xlsx";
  private final String TEMP_FILE_PREFIX = "report";
  private final String TEMP_FILE_SUFFIX = ".xlsx";

  public void generateReportForBank(List<BankRatesDto> bankRatesDtos) throws IOException {
    try (InputStream is = getTemplateInputStream(SINGLE_BANK_RATES_TEMPLATE_PATH)) {
      try (OutputStream os = new FileOutputStream(getTempFile())) {
        Context context = new Context();
        context.putVar("currencies", bankRatesDtos);
        JxlsHelper.getInstance().processTemplate(is, os, context);
      }
    }
  }

  public void generateReportForMultipleBanks(List<List<BankRatesDto>> bankRatesList) throws IOException {
    try (InputStream is = getTemplateInputStream(MULTIPLE_BANKS_RATES_TEMPLATE_PATH)) {
      try (OutputStream os = new FileOutputStream(getTempFile())) {
        Context context = PoiTransformer.createInitialContext();
        context.putVar("bankRates", bankRatesList);
        context.putVar("sheetNames", bankRatesList.stream().flatMap(Collection::stream).map(BankRatesDto::getBankType).filter(Objects::nonNull).map(BankType::name).distinct().collect(Collectors.toList()));
        JxlsHelper.getInstance().processTemplate(is, os, context);
      }
    }
  }

  private InputStream getTemplateInputStream(String templatePath) {
    return getClass().getResourceAsStream(templatePath);
  }

  private File getTempFile() throws IOException {
    return Files.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX).toFile();
  }
}
