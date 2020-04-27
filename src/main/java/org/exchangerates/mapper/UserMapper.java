package org.exchangerates.mapper;

import org.exchangerates.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
  public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    User user = new User();
    user.setId(resultSet.getLong("id"));
    user.setUsername(resultSet.getString("username"));
    user.setPassword(resultSet.getString("password"));
    user.setEmail(resultSet.getString("email"));
    user.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
    return user;
  }
}
