package org.exchangerates.mapper;

import org.exchangerates.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
    user.setId(rs.getLong("id"));
    user.setLogin(rs.getString("login"));
    user.setPassword(rs.getString("password"));
    user.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
    return user;
  }
}
