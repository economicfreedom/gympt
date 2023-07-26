package com.myproject.gympt.user.service;

import com.myproject.gympt.user.db.UserEntity;
import com.myproject.gympt.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceSecurity implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        var _user =  userRepository.findByUid(uid);

        if (_user.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        UserEntity user = _user.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
 if ("admin".equals(uid)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new User(user.getUid(),user.getPassword(), authorities);
    }

}
