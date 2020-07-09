package org.exchangerates.service;

import org.exchangerates.exception.InvalidLoginDataException;
import org.exchangerates.dto.UserDto;
import org.exchangerates.repository.UserRepository;
import org.exchangerates.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EncodingService encodingService;

  public List<UserDto> findAll(){
    return userRepository.getUsers().stream().map(Converter::convertToDto).collect(Collectors.toList());
  }

  public boolean create(UserDto userDto) {
    if (Objects.nonNull(loadUserByUsername(userDto.getUsername()))) {
      return false;
    }
    userDto.setPassword(encodingService.encode(userDto.getPassword()));
    return userRepository.createUser(Converter.convertToEntity(userDto));
  }

  public UserDto loadUserByUsername(String username) {
    return Converter.convertToDto(userRepository.getUser(username));
  }

  public UserDto findById(Long id) throws InvalidLoginDataException {
    return Converter.convertToDto(userRepository.getUser(id));
  }

  public List<UserDto> getSubscribedToNewsletterUsers() {
    return userRepository.getSubscribedToNewsletterUsers().stream().map(Converter::convertToDto).collect(Collectors.toList());
  }

  public boolean checkPassword(UserDto userDto) {
    return encodingService.checkPassword(userDto.getPassword(), loadUserByUsername(userDto.getUsername()).getPassword());
  }
}
