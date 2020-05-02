package org.exchangerates.repository;

import org.exchangerates.model.User;
import org.exchangerates.service.HibernateSessionService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
  @Autowired
  private HibernateSessionService hibernateSessionService;

  private final Class ENTITY_CLASS = User.class;
  private final String TABLE_NAME = "user_data";

  public List<User> getUsers() {
    try (Session session = hibernateSessionService.getSessionFactory(ENTITY_CLASS).openSession()) {
      return session.createQuery("FROM " + TABLE_NAME, ENTITY_CLASS).list();
    }
  }

  public boolean createUser(User user) {
    Transaction transaction = null;
    try (Session session = hibernateSessionService.getSessionFactory(ENTITY_CLASS).openSession()) {
      transaction = session.beginTransaction();
      session.save(user);
      transaction.commit();
      return true;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
    return false;
  }

  public User getUser(String username) {
    try (Session session = hibernateSessionService.getSessionFactory(ENTITY_CLASS).openSession()) {
      Query query=session.createQuery("FROM " + TABLE_NAME + " WHERE username=:username", ENTITY_CLASS);
      query.setParameter("username", username);
      query.executeUpdate();
      return ((User)query.list().stream().findFirst().orElse(null));
    }
  }

  public User getUser(Long id) {
    try (Session session = hibernateSessionService.getSessionFactory(ENTITY_CLASS).openSession()) {
      Query query=session.createQuery("FROM " + TABLE_NAME + " WHERE id=:id", ENTITY_CLASS);
      query.setParameter("id", id);
      query.executeUpdate();
      return ((User)query.list().stream().findFirst().orElse(null));
    }
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
