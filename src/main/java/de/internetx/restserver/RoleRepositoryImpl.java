package de.internetx.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public ArrayList<GrantedAuthority> getRoles(Long userId) {
        return jdbcTemplate.query(Constants.GET_ROLES_BY_ID_QUERY, new Object[]{userId}, resultSet -> {
            if (resultSet.next()) {
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                for (int i = 0; i < Constants.ROLES.length; i++) {
                    String role = Constants.ROLES[i];
                    int hasRole = resultSet.getInt(role);
                    if (hasRole > 0) {
                        authorities.add(new SimpleGrantedAuthority(role.toUpperCase()));
                    }
                }
                return authorities;
            }
            return null;
        });
    }

    @Override
    public void insertRole(Long userId) {
        jdbcTemplate.update(Constants.INSERT_USER_QUERY, userId, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }
}
