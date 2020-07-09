package de.internetx.restserver.security;

import de.internetx.restserver.RoleRepository;
import de.internetx.restserver.user.UserModel;
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
        UserModel userModel = userRepository.getUserByLogin(login);
        if (userModel == null) {
            throw new UsernameNotFoundException(login);
        }

        return new User(userModel.getLogin(), userModel.getPassword(), getAuthorities(userModel.getId()));
    }

    public UsernamePasswordAuthenticationToken getToken(String login) {
        Long userId = userRepository.getUserIdByLogin(login);
        UserModel userModel = userRepository.getUserById(userId);

        return new UsernamePasswordAuthenticationToken(userModel, null, getAuthorities(userId));
    }

    private ArrayList<GrantedAuthority> getAuthorities(Long userId) {
        return roleRepository.getRolesById(userId);
    }
}
