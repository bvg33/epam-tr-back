package com.epam.tr.dao.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "epam.tr_users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole = UserRole.USER;

    public AppUser() {
    }

    public AppUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AppUser(String login, String password, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public AppUser(int id, String login, String password, UserRole userRole) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(login, appUser.login) && Objects.equals(password, appUser.password) && userRole == appUser.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, userRole);
    }
}
