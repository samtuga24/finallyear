package com.example.demo.service;

import com.example.demo.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private long id;
    private String fname;
    private String lname;
    private String uname;
    private String password;
    private LocalDate dateApplied;
    private String phoneNumber;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(long id, String fname, String lname, String uname, String password,String phoneNumber, LocalDate dateApplied,
                            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.password = password;
        this.dateApplied = dateApplied;
        this.phoneNumber = phoneNumber;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Admin admin){
        List<GrantedAuthority> authorities = admin.getRoles().stream()
                .map(roles-> new SimpleGrantedAuthority(roles.getName()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                admin.getId(),
                admin.getFname(),
                admin.getLname(),
                admin.getUname(),
                admin.getPassword(),
                admin.getPhoneNumber(),
                admin.getDateApplied(),

                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return uname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
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
