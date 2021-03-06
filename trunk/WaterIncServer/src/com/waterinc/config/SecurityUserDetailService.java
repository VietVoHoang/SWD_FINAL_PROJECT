package com.waterinc.config;


import com.waterinc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.waterinc.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Asus on 5/10/2017.
 */
@Service
public class SecurityUserDetailService implements UserDetailsService {
    public static String uName = "";

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        uName = username;
        if (user != null) {
            return new SecurityUser(user.getUsername(), user.getPassword(), getAuthorities(user), user.getEnable() == 1, user);
        }
        throw new UsernameNotFoundException(username);
    }

    private Set<GrantedAuthority> getAuthorities(Users user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
        authorities.add(grantedAuthority);
        return authorities;
    }
}
