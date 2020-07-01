package de.internetx.restserver.auth;

import de.internetx.restserver.User;


public interface UserRepository {

    int createUser(User user);

    int updateUser(User user);

    User getUser(Long id);

    int deleteUser(Long id);
}


