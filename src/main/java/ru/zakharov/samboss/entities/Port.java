package ru.zakharov.samboss.entities;

import javax.persistence.*;

@Entity
@Table(name = "ports")
public class Port {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "port")
    private int port;

    @Column(name = "port_state")
    private String portState;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_product")
    private String serviceProduct;

    @ManyToOne
    @JoinColumn(name = "scan_id")
    private Scan scan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Scan getScan() {
        return scan;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

    public String getPortState() {
        return portState;
    }

    public void setPortState(String portState) {
        this.portState = portState;
    }

    public String getServiceProduct() {
        return serviceProduct;
    }

    public void setServiceProduct(String serviceProduct) {
        this.serviceProduct = serviceProduct;
    }
}
