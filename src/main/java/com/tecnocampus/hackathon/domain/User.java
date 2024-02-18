package com.tecnocampus.hackathon.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.hypersistence.tsid.TSID;

import jakarta.persistence.*;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    private final String id = TSID.fast().toString();

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String phone;

    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities = new HashSet<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}