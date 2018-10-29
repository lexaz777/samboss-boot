package ru.zakharov.samboss.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zakharov.samboss.entities.ScanSettings;


import java.util.List;

@Repository
public interface ScanSettingsRepository extends CrudRepository<ScanSettings, Long> {
    public List<ScanSettings> findAll();
    public ScanSettings findScanSettingsById(Long id);
}
