package ru.zakharov.samboss.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zakharov.samboss.entities.Scan;

import java.util.List;

@Repository
public interface ScanRepository extends CrudRepository<Scan, Long> {
    public List<Scan> findAllScansByNetworkObjectId(Long networkObjectId);
}
