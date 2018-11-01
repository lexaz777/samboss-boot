package ru.zakharov.samboss.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.zakharov.samboss.entities.Role;
import ru.zakharov.samboss.entities.SystemUser;
import ru.zakharov.samboss.entities.User;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);

    Collection<User> findAll();

    void save(SystemUser systemUser);

    Collection<Role> findAllRoles();
}
