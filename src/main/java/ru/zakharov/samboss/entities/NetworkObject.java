package ru.zakharov.samboss.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "network_objects")
public class NetworkObject {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ipaddr")
    private String ipaddr;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "os_id")
    private Long osId;

    @OneToMany(mappedBy = "networkObject")
    private List<Scan> scanList;

    public NetworkObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Long getOsId() {
        return osId;
    }

    public void setOsId(Long osId) {
        this.osId = osId;
    }
}
