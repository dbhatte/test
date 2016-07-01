package de.smava.recrt.persistence.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import de.smava.recrt.model.AppUser;

/**
 * AppUser Entity.
 * Created by pvitic on 11.05.15.
 */
@Entity
@Table(name = "app_user")
public class AppUserEntity implements AppUser {

    @Id
    private String username;

    private String email;

    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "key.appUser")
    @JsonBackReference
    private Set<AppUserRoleEntity> appUserRoles;

    public AppUserEntity() {
    }

    public AppUserEntity(String username) {
        super();
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AppUserRoleEntity> getAppUserRoles() {
        return appUserRoles;
    }

    public void setAppUserRoles(Set<AppUserRoleEntity> appUserRoles) {
        this.appUserRoles = appUserRoles;
    }

}
