package de.internetx.restserver.security;

import de.internetx.restserver.RoleRepository;
import de.internetx.restserver.user.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AuthUser authUser = userRepository.getUserByLogin(login);
        if (authUser == null) {
            throw new UsernameNotFoundException(login);
        }

        return new User(authUser.getLogin(), authUser.getPassword(), getAuthorities(authUser.getId()));
    }

    public UsernamePasswordAuthenticationToken getToken(String user) {
        Long userId = userRepository.getUserIdByLogin(user);

        return new UsernamePasswordAuthenticationToken(user, null, getAuthorities(userId));
    }

    private ArrayList<GrantedAuthority> getAuthorities(Long userId) {
        return roleRepository.getRolesById(userId);
    }
}
