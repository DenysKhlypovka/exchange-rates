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
  private final String TABLE_NAME = ENTITY_CLASS.getSimpleName();

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
    return getUsersByFieldCondition(Field.username, username).stream().findFirst().orElse(null);
  }

  public User getUser(Long id) {
    return getUsersByFieldCondition(Field.id, id).stream().findFirst().orElse(null);
  }

  public List<User> getSubscribedToNewsletterUsers() {
    return getUsersByFieldCondition(Field.subscribed_to_newsletter, true);
  }

  private List<User> getUsersByFieldCondition(Field field, Object conditionValue) {
    try (Session session = hibernateSessionService.getSessionFactory(ENTITY_CLASS).openSession()) {

      Query query = session.createQuery("FROM " + TABLE_NAME + " WHERE " + constructHibernateCondition(field), ENTITY_CLASS);
      query.setParameter(field.name(), conditionValue);
      return query.list();
    }
  }

  protected enum Field {
    id, username, subscribed_to_newsletter;
  }

  public String constructHibernateCondition(Field field) {
    var condition = field.name();
    return condition + "=:" + condition;
  }
}
