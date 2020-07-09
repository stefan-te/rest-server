package de.internetx.restserver.models;


public interface UserRepository {

    int createUser(UserModel userModel);

    int updateUser(UserModel userModel);

    int deleteUser(Long id);

    UserModel getUserById(Long id);

    UserModel getUserByLogin(String login);

    Long getUserIdByLogin(String login);
}


