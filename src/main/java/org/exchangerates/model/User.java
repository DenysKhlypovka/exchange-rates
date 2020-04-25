package org.exchangerates.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class User {
  private Long id;
  @NotNull
  private String login;
  @NotNull
  @Size(min = 8, message = "Password length must be at least 8 chars")
  private String password;
  private String hash;
  private String email;
  private LocalDateTime createdDate;

  public User() {
  }

  public User(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public User(String login, String password, String email) {
    this.login = login;
    this.password = password;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
}
