package org.exchangerates.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class UserDto implements UserDetails {
  private Long id;

  @NotNull
  @Size(min = 2, message = "Username length must be at least 2 chars")
  private String username;

  @NotNull
  @Size(min = 8, message = "Password length must be at least 8 chars")
  private String password;

  @Email(message = "Email should be valid")
  private String email;
  private LocalDateTime createdDate;
  private boolean subscribedToNewsletter;

  public UserDto() {
  }

  public UserDto(String username, String password) {
    this.username = username;
    this.password = password;
    this.subscribedToNewsletter = true;
  }

  public UserDto(String email) {
    this.email = email;
    this.subscribedToNewsletter = true;
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

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public boolean isSubscribedToNewsletter() {
    return subscribedToNewsletter;
  }

  public void setSubscribedToNewsletter(boolean subscribedToNewsletter) {
    this.subscribedToNewsletter = subscribedToNewsletter;
  }

  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  public boolean isAccountNonExpired() {
    return true;
  }

  public boolean isAccountNonLocked() {
    return true;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }

  public boolean isEnabled() {
    return true;
  }
}
