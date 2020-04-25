package org.exchangerates;

import org.exchangerates.model.BankType;
import org.exchangerates.service.BankService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.stream.Stream;

class AppTest {
  @Autowired
  BankService bankService;
  @Test
  void shouldConvertZeroKilogramValue() {
    Assertions.assertEquals(BigDecimal.ZERO, BigDecimal.ONE);
  }

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

  @RepeatedTest(3)
  void shouldAlwaysReturnTheSameValue() {
    Assertions.assertEquals(new BigDecimal("29.4840").setScale(4), BigDecimal.ONE);
  }

  @AfterEach
  void tearThis(){
    System.out.println("@AfterEach executed");
  }
}
