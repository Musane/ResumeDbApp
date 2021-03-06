package com.company.service.inter;

import com.company.entity.User;

import java.util.List;

/**
 *
 * @author M
 */
public interface UserServiceInter {

    public List<User> getAll(String name, String surname, Integer nationalityId);

    public User findByEmailAndPassword(String email, String password);

    public User getById(int id);

    public boolean addUser(User u);

    public boolean updateUser(User u);

    public boolean removeUser(int id);

    public User findByEmail(String email);

}
