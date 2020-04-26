package org.exchangerates.repository;

import org.exchangerates.exception.InvalidLoginDataException;
import org.exchangerates.mapper.UserMapper;
import org.exchangerates.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static UserMapper userMapper = new UserMapper();

  public List<User> getUsers() {
    return jdbcTemplate.query("SELECT * FROM user_data ORDER BY id DESC", userMapper);
  }

  public boolean createUser(User user) throws InvalidLoginDataException {
    try {
      return jdbcTemplate.update("INSERT INTO user_data (login, hash, email, created_date) VALUES (?, ?, ?, NOW())", user.getLogin(), user.getHash(), user.getEmail()) != 0;
    } catch (Exception e) {
      throw new InvalidLoginDataException(e.getMessage());
    }
  }

  public User deleteUser(User user) throws InvalidLoginDataException {
    try {
      return jdbcTemplate.update("DELETE FROM user_data WHERE id = ? ", user.getId()) != 0 ? user : null;
    } catch (Exception e) {
      throw new InvalidLoginDataException(e.getMessage());
    }
  }

  public User getUser(String login) {
    return jdbcTemplate.query("SELECT * FROM user_data WHERE login = ? ", new Object[]{login}, userMapper).stream().findFirst().orElse(null);
  }

  public User getUser(Long id) throws InvalidLoginDataException {
    try {
      return jdbcTemplate.queryForObject("SELECT * FROM user_data WHERE id = ? ", new Object[]{id}, userMapper);
    } catch (Exception e) {
      throw new InvalidLoginDataException(e.getMessage());
    }
  }
  //alter table "user_data" add column email varchar(50)
}
