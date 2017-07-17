
package com.alex.dbproject.domain;

import com.alex.dbproject.model.User;
import java.util.List;

public interface UserDao {

    User loadByUsername(String username);
    User getById(Integer id);
    Integer save(User u);
    List<User> list();
    void delete(Integer id);
    void updateUser(User user);
    
}
