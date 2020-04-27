package org.exchangerates.service;

import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.model.BankType;
import org.exchangerates.model.Currency;
import org.exchangerates.model.ExchangeRate;
import org.exchangerates.model.ExchangeRatesTable;
import org.exchangerates.xml.adapter.DateAdapterDash;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankService {

  public BankRatesDto getDataFromBank(String bankTypeStr) {
    var bankType = Enum.valueOf(BankType.class, bankTypeStr);
    Class<? extends ExchangeRatesTable> classType = bankType.getTableClass();
    List<? extends ExchangeRate> rates = classType.cast(parseFromUrl(bankType.getUrl(), classType)).getRates();

    var ratesMap = Stream.of(Currency.values()).collect(Collectors.toMap(Currency::name, currency -> rates.stream()
        .filter(rate -> rate.getCurrencyCode().toLowerCase().equals(currency.name().toLowerCase()))
        .findFirst().map(ExchangeRate::getRate)
        .orElse(BigDecimal.ZERO)));
    return new BankRatesDto(ratesMap, bankType);
  }

  private static <T> Object parseFromUrl(String Url, Class<T> parameterClass) {
    try {
      var jaxbContext = JAXBContext.newInstance(parameterClass);
      var url = new URL(Url);
      var jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      jaxbUnmarshaller.setAdapter(DateAdapterDash.class, new DateAdapterDash());
      return parameterClass.cast(jaxbUnmarshaller.unmarshal(new InputSource(url.openStream())));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
