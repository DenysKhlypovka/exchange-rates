package org.exchangerates;

import org.exchangerates.service.EncodingService;
import org.exchangerates.util.TextUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EncodingServiceTest {
  @Autowired
  private EncodingService encodingService;

  @Test
  void encodingServiceShouldEncode() {
    Assertions.assertNotNull(encodingService.encode(TextUtil.generateRandomPassword()));
  }

  @Test
  void encodingServiceSourceAndDecodedPasswordShouldMatch() {
    String someString = TextUtil.generateRandomPassword();
    Assertions.assertTrue(encodingService.checkPassword(someString, encodingService.encode(someString)));
  }
}
