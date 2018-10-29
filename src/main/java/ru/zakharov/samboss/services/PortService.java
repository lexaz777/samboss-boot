package ru.zakharov.samboss.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zakharov.samboss.entities.Port;
import ru.zakharov.samboss.repositories.PortRepository;

import java.util.List;

@Service
public class PortService {
    private PortRepository portRepository;

    @Autowired
    public void setPortRepository(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    public List<Port> listAllPortsByScanId(Long scanId) {
        return portRepository.findAllPortsByScanId(scanId);
    }

    public void addPortList(List<Port> portList) {
        portRepository.saveAll(portList);
    }
}
