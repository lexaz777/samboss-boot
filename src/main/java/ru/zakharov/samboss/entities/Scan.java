package ru.zakharov.samboss.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "scan")
public class Scan {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name= "network_object_id")
    private NetworkObject networkObject;

    @OneToMany(mappedBy = "scan")
    private List<Port> portList;

    public Scan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NetworkObject getNetworkObject() {
        return networkObject;
    }

    public void setNetworkObject(NetworkObject networkObject) {
        this.networkObject = networkObject;
    }

    public List<Port> getPortList() {
        return portList;
    }

    public void setPortList(List<Port> portList) {
        this.portList = portList;
    }
}
