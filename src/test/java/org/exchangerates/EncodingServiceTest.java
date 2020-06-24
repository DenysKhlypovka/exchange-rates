package org.exchangerates;

import org.exchangerates.service.EncodingService;
import org.exchangerates.util.TextUtil;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EncodingServiceTest {
  @InjectMocks
  private EncodingService encodingService;

  @Test
  public void encodingServiceShouldEncode() {
    Assertions.assertNotNull(encodingService.encode(TextUtil.generateRandomPassword()));
  }

  @Test
  public void encodingServiceSourceAndDecodedPasswordShouldMatch() {
    String someString = TextUtil.generateRandomPassword();
    Assertions.assertTrue(encodingService.checkPassword(someString, encodingService.encode(someString)));
  }
}
