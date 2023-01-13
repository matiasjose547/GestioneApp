package br.com.man.management.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends AbstractEntity {
    private String username;
    private String password;
    private String token;

    public User() {
    }

    public User(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, token);
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", token='" + token + '\'' + '}';
    }
}
