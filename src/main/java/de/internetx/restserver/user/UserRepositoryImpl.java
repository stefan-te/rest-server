package de.internetx.restserver.user;

import de.internetx.restserver.Constants;
import de.internetx.restserver.security.AuthUser;
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
    public int createUser(User user) {
        return jdbcTemplate.update(Constants.INSERT_USER_QUERY,
                user.getLogin(), user.getPassword(), user.getFname(), user.getLname(), user.getEmail());
        // TODO: 01.07.20 insert role entry
    }

    @Override
    public int updateUser(User user) {
        return jdbcTemplate.update(Constants.UPDATE_USER_QUERY,
                user.getLogin(), user.getPassword(), user.getFname(), user.getLname(), user.getEmail(), user.getId());
    }

    @Override
    public User getUserById(Long id) {
        return jdbcTemplate.queryForObject(Constants.GET_USER_BY_ID_QUERY, new Object[]{id}, (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setFname(resultSet.getString("fname"));
            user.setLname(resultSet.getString("lname"));
            user.setEmail(resultSet.getString("email"));
            return user;
        });
    }

    @Override
    public int deleteUser(Long id) {
        return jdbcTemplate.update(Constants.DELETE_USER_QUERY, id);
    }

    @Override
    public AuthUser getUserByLogin(String login) {
        return jdbcTemplate.queryForObject(Constants.GET_USER_BY_LOGIN_QUERY, new Object[]{login}, (resultSet, i) -> {
            AuthUser authUser = new AuthUser();
            authUser.setLogin(resultSet.getString("login"));
            authUser.setPassword(resultSet.getString("password"));
            return authUser;
        });
    }
}
