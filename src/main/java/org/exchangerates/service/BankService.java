package org.exchangerates.service;

import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.model.BankType;
import org.exchangerates.model.Currency;
import org.exchangerates.model.ExchangeRate;
import org.exchangerates.model.ExchangeRatesTable;
import org.exchangerates.util.TextUtil;
import org.exchangerates.xml.XmlParser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankService {
  private BankType bankType;

  public void setBankType(String bankTypeStr) {
    bankType = Enum.valueOf(BankType.class, bankTypeStr);
  }

  public void setBankType(BankType bankType) {
    this.bankType = bankType;
  }

  public BankRatesDto getRatesForDate(LocalDate date) {
    String urlStr = generateUrlStr(date);

    Class<? extends ExchangeRatesTable> classType = bankType.getTableClass();
    ExchangeRatesTable table = classType.cast(XmlParser.parseFromUrl(urlStr, classType));

    if (Objects.isNull(table)) {
      return BankRatesDto.getEmptyInstance(date);
    }

    var ratesMap = Stream.of(Currency.values()).collect(Collectors.toMap(Currency::name, currency -> table.getRates().stream()
        .filter(rate -> rate.getCurrencyCode().toLowerCase().equals(currency.name().toLowerCase()))
        .findFirst().map(ExchangeRate::getRate)
        .orElse(BigDecimal.ZERO)));
    return new BankRatesDto(ratesMap, bankType, table.getDate());
  }

  public List<BankRatesDto> getRatesForPeriod(LocalDate from, LocalDate to) {
    List<BankRatesDto> rates = new LinkedList<>();
    for (LocalDate date = from; date.isBefore(to); date = date.plusDays(1)) {
      rates.add(getRatesForDate(date));
    }
    return rates;
  }

  private String generateUrlStr(LocalDate date) {
    return Objects.isNull(date) ? bankType.getCurrentRatesUrl() : String.format(bankType.getRatesForDateUrl(), TextUtil.localDateToString(date, bankType.getDateFormat().getPattern()));
  }
}
