package ru.zakharov.samboss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.zakharov.samboss.entities.Port;
import ru.zakharov.samboss.services.PortService;


import java.util.List;

@Controller
@RequestMapping("/port")
public class PortController {
    private PortService portService;

    @Autowired
    public void setPortService(PortService portService) {
        this.portService = portService;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String getAllPortsByScanId(Model model, @RequestParam int id) {
        List<Port> portList = portService.listAllPortsByScanId((long) id);
        model.addAttribute("portList", portList);
        return "scan-results";
    }
}
