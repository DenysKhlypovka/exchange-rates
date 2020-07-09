package org.exchangerates.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
  @Autowired
  private JavaMailSender javaMailSender;

  private final String FILE_NAME = "daily_rates.xls";
  private final String EMAIL_SUBJECT = "Your Daily Exchange Rates";
  private final String EMAIL_TEXT = "";

  public void sendEmailWithAttachment(String recipientEmail, String fileAbsPath) {

    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
      messageHelper.setTo(recipientEmail);
      messageHelper.setSubject(EMAIL_SUBJECT);
      messageHelper.addAttachment(FILE_NAME, new ClassPathResource(fileAbsPath));
      javaMailSender.send(mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error(e.getMessage());
    }
  }

  public void sendEmail(String recipientEmail) {

    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(recipientEmail);
    mailMessage.setSubject(EMAIL_SUBJECT);
    mailMessage.setText(EMAIL_SUBJECT);
    javaMailSender.send(mailMessage);
  }
}
