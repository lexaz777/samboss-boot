package ru.zakharov.samboss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zakharov.samboss.entities.Scan;
import ru.zakharov.samboss.services.ScanService;


import java.util.List;

@Controller
@RequestMapping("/scan")
public class ScanController {
    private ScanService scanService;

    @Autowired
    public void setScanService(ScanService scanService) {
        this.scanService = scanService;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String getAllScans(Model model) {
        List<Scan> scanList = scanService.getAllScans();
        model.addAttribute("scanList", scanList);
        return "scans";
    }

    @RequestMapping(path = "/show", method = RequestMethod.GET)
    public String getAllScansById(Model model, @RequestParam int id) {
        List<Scan> scanList = scanService.getAllScansByHostId((long) id);
        model.addAttribute("scanList", scanList);
        return "scans";
    }
}
