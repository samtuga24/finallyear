package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    private final String USER_NOT_FOUND = "The user with Staff ID %s cannot be found";
    @Override
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUname(uname)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,uname)));
        return UserDetailsImpl.build(admin);
    }
}
