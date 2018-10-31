package ru.zakharov.samboss.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.zakharov.samboss.entities.Role;

import java.util.Collection;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String theRoleName);
    Collection<Role> findAll();
}
