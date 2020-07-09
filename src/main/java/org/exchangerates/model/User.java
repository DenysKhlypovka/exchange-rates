package org.exchangerates.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_data")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "created_date")
  private LocalDateTime created_date;

  @Column(name = "subscribed_to_newsletter")
  private boolean subscribedToNewsletter;

  public User() {
    created_date = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDateTime getCreated_date() {
    return created_date;
  }

  public void setCreated_date(LocalDateTime created_date) {
    this.created_date = created_date;
  }

  public boolean isSubscribedToNewsletter() {
    return subscribedToNewsletter;
  }

  public void setSubscribedToNewsletter(boolean subscribedToNewsletter) {
    this.subscribedToNewsletter = subscribedToNewsletter;
  }
}
