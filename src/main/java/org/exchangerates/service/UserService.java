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
  UserRepository userRepository;

  public List<User> getUsers(){
    return userRepository.getUsers();
  }

  public boolean createUser(User user) throws InvalidLoginDataException {
    return userRepository.createUser(user.getLogin(), user.getPassword());
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
}
