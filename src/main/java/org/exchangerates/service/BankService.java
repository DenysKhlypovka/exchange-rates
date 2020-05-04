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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankService {
  private BankType bankType;

  public void setBankType(String bankTypeStr) {
    bankType = Enum.valueOf(BankType.class, bankTypeStr);
  }

  private BankRatesDto getRates(String URL) {
    Class<? extends ExchangeRatesTable> classType = bankType.getTableClass();
    List<? extends ExchangeRate> rates = Optional.ofNullable(classType.cast(XmlParser.parseFromUrl(URL, classType))).map(ExchangeRatesTable::getRates).orElse(Collections.emptyList());

    var ratesMap = Stream.of(Currency.values()).collect(Collectors.toMap(Currency::name, currency -> rates.stream()
        .filter(rate -> rate.getCurrencyCode().toLowerCase().equals(currency.name().toLowerCase()))
        .findFirst().map(ExchangeRate::getRate)
        .orElse(BigDecimal.ZERO)));
    return new BankRatesDto(ratesMap, bankType);
  }

  public BankRatesDto getCurrentRates() {
    return getRates(bankType.getCurrentRatesUrl());
  }

  public List<BankRatesDto> getRatesForPeriod(LocalDate from, LocalDate to) {
    List<BankRatesDto> rates = new LinkedList<>();
    for (LocalDate date = from; date.isBefore(to); date = date.plusDays(1)) {
      rates.add(getRatesForDate(date));
    }
    return rates;
  }

  public BankRatesDto getRatesForDate(LocalDate date) {
    return Optional.ofNullable(date).map(_date -> getRates(String.format(bankType.getRatesForDateUrl(), TextUtil.localDateToString(_date, bankType.getDateFormat().getPattern())))).orElse(getCurrentRates());
  }
}
