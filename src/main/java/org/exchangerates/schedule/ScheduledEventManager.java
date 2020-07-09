package org.exchangerates.schedule;

import org.apache.logging.log4j.util.Strings;
import org.exchangerates.dto.BankRatesDto;
import org.exchangerates.dto.UserDto;
import org.exchangerates.mail.MailService;
import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.exchangerates.service.UserService;
import org.exchangerates.service.XlsExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ScheduledEventManager {
  @Autowired
  UserService userService;
  @Autowired
  XlsExportService xlsExportService;
  @Autowired
  BankService bankService;
  @Autowired
  MailService mailService;

  @Scheduled(cron = "0 0 12 * * *")
  public void sendDailyRates() {
    List<List<BankRatesDto>> bankRates = Stream.of(BankType.values()).map(bankType -> {
      bankService.setBankType(bankType);
      return bankService.getRatesForPeriod(LocalDate.now().minusDays(10), LocalDate.now().minusDays(6));
    }).collect(Collectors.toList());

    String generatedFileName = xlsExportService.generateReportForMultipleBanks(bankRates);
    userService.getSubscribedToNewsletterUsers().stream()
            .map(UserDto::getEmail)
            .filter(Strings::isNotBlank)
            .forEach(email -> {
              mailService.sendEmailWithAttachment(email, generatedFileName);
            });
  }
}
