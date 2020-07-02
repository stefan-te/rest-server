package de.internetx.restserver.user;


public interface UserRepository {

    int createUser(User user);

    int updateUser(User user);

    User getUser(Long id);

    int deleteUser(Long id);
}


