package de.internetx.restserver.models;

import de.internetx.restserver.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createUser(UserModel userModel) {
        return jdbcTemplate.update(Constants.INSERT_USER_QUERY,
                userModel.getLogin(), userModel.getPassword(), userModel.getFname(), userModel.getLname(), userModel.getEmail());
    }

    @Override
    public int updateUser(UserModel userModel) {
        return jdbcTemplate.update(Constants.UPDATE_USER_QUERY,
                userModel.getLogin(), userModel.getPassword(), userModel.getFname(), userModel.getLname(), userModel.getEmail(), userModel.getId());
    }

    @Override
    public UserModel getUserById(Long id) {
        return jdbcTemplate.query(Constants.GET_USER_BY_ID_QUERY, new Object[]{id}, (resultSet) -> {
            if (resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getLong("id"));
                userModel.setLogin(resultSet.getString("login"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setFname(resultSet.getString("fname"));
                userModel.setLname(resultSet.getString("lname"));
                userModel.setEmail(resultSet.getString("email"));
                return userModel;
            }
            return null;
        });
    }

    @Override
    public int deleteUser(Long id) {
        return jdbcTemplate.update(Constants.DELETE_USER_QUERY, id);
    }

    @Override
    public UserModel getUserByLogin(String login) {
        return jdbcTemplate.query(Constants.GET_USER_BY_LOGIN_QUERY, new Object[]{login}, (resultSet) -> {
            if (resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getLong("id"));
                userModel.setLogin(resultSet.getString("login"));
                userModel.setPassword(resultSet.getString("password"));
                return userModel;
            }
            return null;
        });
    }

    @Override
    public Long getUserIdByLogin(String login) {
        return jdbcTemplate.query(Constants.GET_USER_BY_LOGIN_QUERY, new Object[]{login}, (resultSet) -> {
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
            return null;
        });
    }
}
