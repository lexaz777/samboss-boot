package ru.zakharov.samboss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zakharov.samboss.services.NetworkObjectService;


@Controller
@RequestMapping("/host")
public class NetworkObjectController {
    private NetworkObjectService networkObjectService;

    @Autowired
    public void setNetworkObjectService(NetworkObjectService networkObjectService) {
        this.networkObjectService = networkObjectService;
    }

    @Secured("ROLE_USER")
    @RequestMapping("/list")
    public String getAllHosts(Model model) {
        model.addAttribute("listHosts", networkObjectService.getAllNetworkObjects());
        return "hosts";
    }
}
