package ru.zakharov.samboss.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zakharov.samboss.entities.ScanSettings;
import ru.zakharov.samboss.repositories.ScanSettingsRepository;


import java.util.List;

@Service
public class ScanSettingsService {
    private ScanSettingsRepository scanSettingsRepository;

    @Autowired
    public void setScanSettingsRepository(ScanSettingsRepository scanSettingsRepository) {
        this.scanSettingsRepository = scanSettingsRepository;
    }

    public List<ScanSettings> showAllScanSettings() {
        return scanSettingsRepository.findAll();
    }

    public void addScanSettings(ScanSettings scanSettings) {
        if (scanSettings == null) return;
        scanSettingsRepository.save(scanSettings);
    }

    public void removeScanSettings(int id) {
        scanSettingsRepository.deleteById((long) id);
    }

    public ScanSettings findScanSettingsById(Long scanSettingsId) {
        return scanSettingsRepository.findScanSettingsById(scanSettingsId);
    }

    public void save(ScanSettings scanSettings) {
        if (scanSettings == null) return;
        scanSettingsRepository.save(scanSettings);
    }

}
