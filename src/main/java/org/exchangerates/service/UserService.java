package org.exchangerates.service;

import org.exchangerates.exception.InvalidLoginDataException;
import org.exchangerates.model.User;
import org.exchangerates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EncodingService encodingService;

  public List<User> findAll(){
    return userRepository.getUsers();
  }

  public boolean create(User user) throws InvalidLoginDataException {
    if (Objects.nonNull(loadUserByUsername(user.getUsername()))) {
      return false;
    }
    user.setPassword(encodingService.encode(user.getPassword()));
    return userRepository.createUser(user);
  }

  public User remove(String username) throws InvalidLoginDataException {
    return userRepository.deleteUser(loadUserByUsername(username));
  }

  public User loadUserByUsername(String username) {
    return userRepository.getUser(username);
  }

  public User findById(Long id) throws InvalidLoginDataException {
    return userRepository.getUser(id);
  }

  public boolean checkPassword(User user) {
    return encodingService.checkPassword(user.getPassword(), loadUserByUsername(user.getUsername()).getPassword());
  }
}
