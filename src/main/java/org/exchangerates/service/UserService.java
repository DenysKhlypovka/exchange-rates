package org.exchangerates.service;

import org.exchangerates.exception.InvalidLoginDataException;
import org.exchangerates.model.User;
import org.exchangerates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EncodingService encodingService;

  public List<User> findAll(){
    return userRepository.getUsers();
  }

  public boolean create(User user) throws InvalidLoginDataException {
    if (Objects.nonNull(find(user.getLogin()))) {
      return false;
    }
    user.setHash(encodingService.encode(user.getPassword()));
    return userRepository.createUser(user);
  }

  public User remove(String login) throws InvalidLoginDataException {
    return userRepository.deleteUser(find(login));
  }

  public User find(String login) {
    return userRepository.getUser(login);
  }

  public User findById(Long id) throws InvalidLoginDataException {
    return userRepository.getUser(id);
  }

  public boolean checkPassword(User user) {
    return encodingService.checkPassword(user.getPassword(), find(user.getLogin()).getHash());
  }

  public void authenticate(User user) {
    if (checkPassword(user)) {
      //generate token
    }
  }
}
