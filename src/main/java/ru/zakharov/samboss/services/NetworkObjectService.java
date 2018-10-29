package ru.zakharov.samboss.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zakharov.samboss.entities.NetworkObject;
import ru.zakharov.samboss.repositories.NetworkObjectRepository;

import java.util.List;

@Service
public class NetworkObjectService {
    private NetworkObjectRepository networkObjectRepository;

    @Autowired
    public void setNetworkObjectRepository(NetworkObjectRepository networkObjectRepository) {
        this.networkObjectRepository = networkObjectRepository;
    }

    public NetworkObjectService() {
    }

    public List<NetworkObject> getAllNetworkObjects() {
        return (List<NetworkObject>) networkObjectRepository.findAll();
    }

    public void addNetworkObject(NetworkObject networkObject) {
        if (networkObject == null) return;
        networkObjectRepository.save(networkObject);
    }

    public NetworkObject getNetworkObjectByIp(String addr) {
        return networkObjectRepository.findNetworkObjectByIpaddrEquals(addr);
    }
}
