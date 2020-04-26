package org.exchangerates;

import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

class AppTest {
  @Autowired
  BankService bankService;

  @BeforeEach
  void setupThis(){
    System.out.println("@BeforeEach executed");
  }

  @Tag("PROD")
  @Disabled
  @Test
  void testCalcTwo()
  {
    Stream.of(BankType.values()).forEach(bankType -> bankService.getDataFromBank(bankType.name()));
  }

  @AfterEach
  void tearThis(){
    System.out.println("@AfterEach executed");
  }
}
