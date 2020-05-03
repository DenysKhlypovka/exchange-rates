package org.exchangerates.util;

import org.exchangerates.model.User;
import org.exchangerates.model.UserDto;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class Converter {

  public static UserDto convertToDto(User user) {
    return Optional.ofNullable(user).map(userToConvert -> new ModelMapper().map(userToConvert, UserDto.class)).orElse(null);
  }

  public static User convertToEntity(UserDto userDto) {
    return Optional.ofNullable(userDto).map(userDtoToConvert -> new ModelMapper().map(userDtoToConvert, User.class)).orElse(null);
  }
}
