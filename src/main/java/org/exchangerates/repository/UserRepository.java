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
      return jdbcTemplate.update("INSERT INTO user_data (username, password, email, created_date) VALUES (?, ?, ?, NOW())", user.getUsername(), user.getPassword(), user.getEmail()) != 0;
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

  public User getUser(String username) {
    return jdbcTemplate.query("SELECT * FROM user_data WHERE username = ? ", new Object[]{username}, userMapper).stream().findFirst().orElse(null);
  }

  public User getUser(Long id) throws InvalidLoginDataException {
    try {
      return jdbcTemplate.queryForObject("SELECT * FROM user_data WHERE id = ? ", new Object[]{id}, userMapper);
    } catch (Exception e) {
      throw new InvalidLoginDataException(e.getMessage());
    }
  }
  //CREATE TABLE user_data (
  //   ID serial PRIMARY KEY,
  //   username VARCHAR (255) NOT NULL,
  //   password VARCHAR (255) NOT NULL,
  //	 created_date TIMESTAMP NOT NULL,
  //    email varchar(50)
  //);
  //ALTER TABLE user_data ADD CONSTRAINT unique_login UNIQUE (username);
}
