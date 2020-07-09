package de.internetx.restserver.user;


public interface UserRepository {

    int createUser(UserModel userModel);

    int updateUser(UserModel userModel);

    UserModel getUserById(Long id);

    int deleteUser(Long id);

    UserModel getUserByLogin(String login);

    Long getUserIdByLogin(String login);
}


