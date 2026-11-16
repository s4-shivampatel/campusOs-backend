package com.campusos.campusos.security;

import com.campusos.campusos.common.exception.ResourceNotFoundException;
import com.campusos.campusos.user.entity.User;
import com.campusos.campusos.user.entity.UserRole;
import com.campusos.campusos.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    @NullMarked
    public UserDetails loadUserByUsername( String username) throws UsernameNotFoundException {
        User user =userRepository.findByEmail(username).orElseThrow(
                ()-> new ResourceNotFoundException("User not found with username "+username)
        );

        List<GrantedAuthority> authorities=
                user.getUserRole()
                        .stream()
                        .<GrantedAuthority>map(userRole -> new SimpleGrantedAuthority(
                                userRole.getRole().getName()
                                )
                        ).toList();

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
//                .roles(user.getUserRole().stream()
//                        .map(UserRole::getRole).toString())
                .build();
    }
}
