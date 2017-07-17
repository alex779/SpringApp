
package com.alex.dbproject.domain;

import com.alex.dbproject.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory factory;

    @Override
    @Transactional
    public User loadByUsername(String username) {
        return (User) factory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("email", username)).uniqueResult();
    }

    @Override
    @Transactional
    public User getById(Integer id) {
        return (User) factory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    @Transactional
    public Integer save(User u) {
        return (Integer) factory.getCurrentSession().save(u);
    }

    @Override
    @Transactional
    public List<User> list() {
        return factory.getCurrentSession().createCriteria(User.class).list();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Session session = factory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        session.delete(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        factory.getCurrentSession().saveOrUpdate(user);
    }

}
