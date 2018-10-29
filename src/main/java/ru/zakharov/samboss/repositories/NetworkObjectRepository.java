package ru.zakharov.samboss.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zakharov.samboss.entities.NetworkObject;


@Repository
public interface NetworkObjectRepository extends CrudRepository<NetworkObject, Long> {
    NetworkObject findNetworkObjectByIpaddrEquals(String hostname);
}
