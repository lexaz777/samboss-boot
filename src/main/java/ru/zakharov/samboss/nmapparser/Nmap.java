package ru.zakharov.samboss.nmapparser;


import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.zakharov.samboss.entities.NetworkObject;
import ru.zakharov.samboss.entities.Port;
import ru.zakharov.samboss.entities.Scan;
import ru.zakharov.samboss.services.NetworkObjectService;
import ru.zakharov.samboss.services.PortService;
import ru.zakharov.samboss.services.ScanService;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Nmap {

    private NetworkObjectService networkObjectService;
    private ScanService scanService;
    private PortService portService;

    public void setNetworkObjectService(NetworkObjectService networkObjectService) {
        this.networkObjectService = networkObjectService;
    }

    public void setScanService(ScanService scanService) {
        this.scanService = scanService;
    }

    public void setPortService(PortService portService) {
        this.portService = portService;
    }

    private String addr = " not def ";
    private String protocol = " not def ";
    private String portId = " not def ";
    private String state = " not def ";
    private String service = " not def ";
    private String product = " not def ";
    private String portState = " not def ";

    private NetworkObject networkObject;
    private Scan newScan = new Scan();
    private List<Port> portList = new ArrayList<>();
    private Scan scan;

    public void parse(String fileName) {
        try {

            StringBuffer out = new StringBuffer();

            //Get the DOM Builder Factory
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            //Get the DOM Builder
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Load and Parse the XML document
            //document contains the complete XML as a Tree.
            Document document =
                    builder.parse("D:\\samboss\\scans\\" + fileName);
            //document = builder.parse("E:\\wyse-wdm-result.xml");


            //Iterating through the nodes and extracting the data.
            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                String nodeName = node.getNodeName();

                if (nodeName != null && nodeName.equals("host")) {
                    NodeList hostNodes = node.getChildNodes();

                    for (int hostNodeRunner = 0; hostNodeRunner < hostNodes.getLength(); hostNodeRunner++) {

                        Node nodeHost = hostNodes.item(hostNodeRunner);
                        String nodeNameHost = nodeHost.getNodeName();

                        System.out.println("hosts: " + nodeNameHost);

                        if (nodeNameHost != null && nodeNameHost.equals("address")) {
                            addr = nodeHost.getAttributes().getNamedItem("addr").getNodeValue();
                            System.out.println("address = " + addr);

                            networkObject = networkObjectService.getNetworkObjectByIp(addr);
                            scan = new Scan();
                            portList.clear();
                            scan.setDate(new Date());
                            if (networkObject == null) {
                                networkObject = new NetworkObject();
                                networkObject.setIpaddr(addr);
                                networkObjectService.addNetworkObject(networkObject);
                            }
                            scan.setNetworkObject(networkObject);
                            scanService.addScan(scan);
                        }

                        if (nodeNameHost != null && nodeNameHost.equals("ports")) {
                            NodeList ports = nodeHost.getChildNodes();
                            for (int portRunner = 0; portRunner < ports.getLength(); portRunner++) {
                                Node nodePort = ports.item(portRunner);
                                // we are now on the port level
                                String name = nodePort.getNodeName();
                                System.out.println("hosts:ports " + name);
                                if (name != null && name.equalsIgnoreCase("port")) {
                                    protocol = nodePort.getAttributes().getNamedItem("protocol").getNodeValue();
                                    portId = nodePort.getAttributes().getNamedItem("portid").getNodeValue();

                                    NodeList portSubNodes = nodePort.getChildNodes();
                                    for (int portSubNodesRunner = 0; portSubNodesRunner < portSubNodes.getLength() - 1; portSubNodesRunner++) {
                                        Node portNode = portSubNodes.item(portSubNodesRunner);
                                        // state
                                        // service
                                        if (portNode != null && portNode.hasAttributes()) {
                                            if (portNode.getNodeName().equalsIgnoreCase("state")) {
                                                state = portNode.getAttributes().getNamedItem("state").getNodeValue();
                                            }
                                            if (portNode.getNodeName().equalsIgnoreCase("service")) {
                                                Node serviceNameNode = portNode.getAttributes().getNamedItem("name");
                                                if (serviceNameNode== null) {
                                                    service = "not defined";
                                                } else {
                                                    service = serviceNameNode.getNodeValue();
                                                }
                                                Node serviceProductNode = portNode.getAttributes().getNamedItem("product");
                                                if (serviceProductNode== null) {
                                                    product = "not defined";
                                                } else {
                                                    product = serviceProductNode.getNodeValue();
                                                }
                                            }
                                        }
                                    }
                                    Port port = new Port();
                                    port.setScan(scan);
                                    port.setServiceName(service);
                                    port.setPortState(state);
                                    port.setServiceProduct(product);
                                    port.setPort(Integer.parseInt(portId));
                                    portList.add(port);
                                    System.out.println("portId = " + portId + " protocol = " + protocol + " state = " + state + " service = " + service);
                                }
                            } // loop through all ports
                        }   // check, if we are within the ports node

                        //scan.setPortList(portList);
                    }
                    portService.addPortList(portList);
                }
            }
            System.out.println(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doScan() {
        Nmap4jScan nmap4jScan = new Nmap4jScan();
        nmap4jScan.doSimpleScan();
    }
}
