package de.internetx.restserver;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

public interface RoleRepository {

    ArrayList<GrantedAuthority> getRoles(Long userId);

    void insertRole(Long userId);
}
