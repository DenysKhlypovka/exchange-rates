package org.exchangerates.util;

import org.exchangerates.model.User;
import org.exchangerates.model.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Converter {
  @Autowired
  private static ModelMapper modelMapper;

  public static UserDto convertToDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }

  public static User convertToEntity(UserDto userDto) {
    return modelMapper.map(userDto, User.class);
  }
}
