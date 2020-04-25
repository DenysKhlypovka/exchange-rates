package org.exchangerates.service;

import org.exchangerates.exception.InvalidLoginDataException;
import org.exchangerates.model.User;
import org.exchangerates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EncodingService encodingService;

  public List<User> getUsers(){
    return userRepository.getUsers();
  }

  public boolean createUser(User user) throws InvalidLoginDataException {
    user.setHash(encodingService.encode(user.getPassword()));
    return userRepository.createUser(user);
  }

  public User deleteUser(String login) throws InvalidLoginDataException {
    return userRepository.deleteUser(getUser(login));
  }

  public User getUser(String login) {
    try {
      return userRepository.getUser(login);
    } catch (InvalidLoginDataException e) {
      System.out.println("error");
      return null;
    }
  }

  public User getUser(Long id) throws InvalidLoginDataException {
    return userRepository.getUser(id);
  }

  public boolean checkPassword(User user) {
    return encodingService.checkPassword(user.getPassword(), getUser(user.getLogin()).getHash());
  }
}
