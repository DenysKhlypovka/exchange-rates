package org.exchangerates;

import org.exchangerates.mail.MailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource("classpath:MailServiceTest.properties")
public class MailServiceTest {
  @Value("${email.for.testing}")
  private String recipientEmailTest;

  @InjectMocks
  MailService mailService;
  @InjectMocks
  JavaMailSender javaMailSender;

  @Before
  public void initMocks(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void emailShouldBeSent() {
    mailService.sendEmail(recipientEmailTest);
  }
}
