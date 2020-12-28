package com.pineapple.tasktracker.service;

import com.pineapple.tasktracker.model.User;
import com.pineapple.tasktracker.model.enums.Role;
import com.pineapple.tasktracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
        Optional<User> optionalUser = userRepository.findByName(username);
        if (optionalUser.isPresent() == false) {
        	System.out.println("Hello " + username);
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        User user = optionalUser.get();
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                mapRolesToAuthorities(Collections.singleton(user.getRole())));
	}
}
