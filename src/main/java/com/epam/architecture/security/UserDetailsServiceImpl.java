package com.epam.architecture.security;

import com.epam.architecture.model.User;
import com.epam.architecture.repository.JpaUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JpaUserRepository repository;

    public UserDetailsServiceImpl(JpaUserRepository repository) {
        this.repository = repository;
    }

    public static UserDetails fromUser(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findById(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return fromUser(user);
    }
}
