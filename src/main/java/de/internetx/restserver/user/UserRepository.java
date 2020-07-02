package de.internetx.restserver.user;


import de.internetx.restserver.security.AuthUser;

public interface UserRepository {

    int createUser(User user);

    int updateUser(User user);

    User getUserById(Long id);

    int deleteUser(Long id);

    AuthUser getUserByLogin(String login);
}


