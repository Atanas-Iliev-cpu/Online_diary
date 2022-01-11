package com.example.myproject.services;

import com.example.myproject.data.models.Role;
import com.example.myproject.data.models.User;
import com.example.myproject.data.repositories.RoleRepository;
import com.example.myproject.data.repositories.UserRepository;
import com.example.myproject.services.models.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserServiceInt{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;



    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public void register(UserServiceModel user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        User u1=this.modelMapper.map(user, User.class);
        u1.setRoles(getRolesForRegistration());
        this.userRepository.saveAndFlush(u1);
    }

    private Set<Role> getRolesForRegistration() {
        Set<Role> roles = new HashSet<>();

        if(this.userRepository.count() == 0) {
            roles.add(this.roleRepository.findByName("ADMIN"));
        } else {
            roles.add(this.roleRepository.findByName("USER"));

        }

        return roles;
    }

    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(s);
        Set<GrantedAuthority> authorities = new HashSet<>();

        return new org.springframework.security.core.userdetails.User(user.getUsername()
                ,user.getPassword(),authorities);

    }
}
