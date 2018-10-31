package ru.zakharov.samboss.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.zakharov.samboss.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
        User findOneByUserName(String userName);
}
