package com.epam.tr.service;

import com.epam.tr.dao.UserDaoImpl;
import com.epam.tr.dao.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SecurityUserDetailService implements UserDetailsService {
    private UserDaoImpl dao;

    @Autowired
    public SecurityUserDetailService(UserDaoImpl dao) {
        this.dao = dao;
    }


    private static final String USER_NOT_FOUND = "User not found";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = dao.getUserByNickname(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + appUser.getUserRole().toString()));
        return new User(appUser.getLogin(), appUser.getPassword(), authorities);
    }
}