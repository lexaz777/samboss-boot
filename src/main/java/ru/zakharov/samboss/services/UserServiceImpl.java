package ru.zakharov.samboss.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zakharov.samboss.entities.Role;
import ru.zakharov.samboss.entities.SystemUser;
import ru.zakharov.samboss.entities.User;
import ru.zakharov.samboss.repositories.RoleRepository;
import ru.zakharov.samboss.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User findByUserName(String userName) {
        return userRepository.findOneByUserName(userName);
    }

    @Override
    @Transactional
    public Collection<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void save(SystemUser systemUser) {
        User user = new User();
        Role defaultUserRole = roleRepository.findOneByName("ROLE_USER");
        user.setUserName(systemUser.getUserName());
        user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        user.setFirstName(systemUser.getFirstName());
        user.setLastName(systemUser.getLastName());
        user.setEmail(systemUser.getEmail());

        //user.setRoles(Arrays.asList(roleRepository.findOneByName("ROLE_USER")));
        String systemUserRole = systemUser.getRole();
        Role newUserRole = roleRepository.findOneByName(systemUserRole);
        if (newUserRole == null) newUserRole = defaultUserRole;
        user.setRoles(Collections.singletonList(newUserRole));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findOneByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}