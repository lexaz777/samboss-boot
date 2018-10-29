package ru.zakharov.samboss.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zakharov.samboss.entities.Scan;
import ru.zakharov.samboss.repositories.ScanRepository;


import java.util.List;

@Service
public class ScanService {
    private ScanRepository scanRepository;

    @Autowired
    public void setScanRepository(ScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    public List<Scan> getAllScansByHostId(Long id) {
        return scanRepository.findAllScansByNetworkObjectId(id);
    }

    public List<Scan> getAllScans() {
        return (List<Scan>) scanRepository.findAll();
    }

    public void addScan(Scan scan) {
        if (scan == null) return;
        scanRepository.save(scan);
    }
}
