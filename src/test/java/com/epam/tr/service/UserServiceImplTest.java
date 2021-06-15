package com.epam.tr.service;

import com.epam.tr.dao.UserDaoImpl;
import com.epam.tr.dao.entities.AppUser;
import com.epam.tr.exceptions.WrongUserCredentials;
import com.epam.tr.service.logic.UserValidator;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.powermock.api.mockito.PowerMockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserDaoImpl dao = Mockito.mock(UserDaoImpl.class);
    @Mock
    private UserValidator validator = Mockito.mock(UserValidator.class);
    @InjectMocks
    private UserServiceImpl service = new UserServiceImpl(dao, validator);

    private List<AppUser> users = Arrays.asList(new AppUser("login1", "password1"),
            new AppUser("login2", "password2"));

    @Test
    public void testGetAllUsers() {
        when(dao.getAll()).thenReturn(users);
        List<AppUser> actual = service.getAllUsers();
        assertEquals(users, actual);
    }

    @Test
    public void testCreate() throws WrongUserCredentials {
        List<AppUser> actual = new ArrayList<>(users);
        AppUser newUser = new AppUser("login3", "password3");
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                return actual.add(newUser);
            }
        }).when(dao).insert(newUser);
        when(validator.isValid(Mockito.anyObject())).thenReturn(true);
        service.create(newUser);
        List<AppUser> expected = Arrays.asList(new AppUser("login1", "password1"),
                new AppUser("login2", "password2"),
                new AppUser("login3", "password3"));
        assertEquals(actual, expected);
    }

    @Test
    public void testDelete() throws WrongUserCredentials {
        List<AppUser> actual = new ArrayList<>(users);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                return actual.remove(0);
            }
        }).when(dao).delete(Mockito.anyObject());
        when(validator.isValid(Mockito.anyObject())).thenReturn(true);
        service.delete(new AppUser("login1", "password1"));
        List<AppUser> expected = Arrays.asList(new AppUser("login2", "password2"));
        assertEquals(expected,actual);
    }

}
