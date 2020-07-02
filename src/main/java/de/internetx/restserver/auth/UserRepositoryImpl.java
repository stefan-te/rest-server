package de.internetx.restserver.auth;

import de.internetx.restserver.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static de.internetx.restserver.Constants.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createUser(User user) {
        return jdbcTemplate.update(INSERT_USER_QUERY,
                user.getLogin(), user.getPassword(), user.getFname(), user.getLname(), user.getEmail());
        // TODO: 01.07.20 insert role entry
    }

    @Override
    public int updateUser(User user) {
        return jdbcTemplate.update(UPDATE_USER_QUERY,
                user.getLogin(), user.getPassword(), user.getFname(), user.getLname(), user.getEmail(), user.getId());
    }

    @Override
    public User getUser(Long id) {
        return jdbcTemplate.queryForObject(GET_USER_QUERY, new Object[]{id}, (resultSet, i) -> {
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
        return jdbcTemplate.update(DELETE_USER_QUERY, id);
    }
}
