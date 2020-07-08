package de.internetx.restserver.security;

import de.internetx.restserver.RoleRepository;
import de.internetx.restserver.user.UserRepository;
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

        // get authorities
        ArrayList<GrantedAuthority> authorities = roleRepository.getRoles(authUser.getId());

        return new User(authUser.getLogin(), authUser.getPassword(), authorities);
    }
}
