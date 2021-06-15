package com.epam.tr.service;

import com.epam.tr.dao.UserDaoImpl;
import com.epam.tr.dao.entities.AppUser;
import com.epam.tr.dao.entities.UserRole;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SecurityUserDetailServiceTest {
    @Mock
    private UserDaoImpl dao = Mockito.mock(UserDaoImpl.class);

    @InjectMocks
    private SecurityUserDetailService service = new SecurityUserDetailService(dao);

    @Test
    public void testLoadUserByUsername() {
        AppUser appUser = new AppUser("login1", "password1", UserRole.USER);
        when(dao.getUserByNickname(Mockito.anyString())).thenReturn(appUser);
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + appUser.getUserRole().toString()));
        UserDetails expected = new User(appUser.getLogin(), appUser.getPassword(), authorities);
        UserDetails actual = service.loadUserByUsername("login1");
        assertEquals(expected, actual);
    }
}
