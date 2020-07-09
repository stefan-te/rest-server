package de.internetx.restserver.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

public interface RoleRepository {

    ArrayList<GrantedAuthority> getRolesById(Long userId);

    void insertRole(Long userId);
}
