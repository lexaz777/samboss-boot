package ru.zakharov.samboss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zakharov.samboss.entities.ScanSettings;
import ru.zakharov.samboss.entities.Target;
import ru.zakharov.samboss.services.ScanSettingsService;
import ru.zakharov.samboss.services.TargetService;

import java.util.List;

@Controller
@RequestMapping("/scansettings")
public class ScanSettingsController {
    private ScanSettingsService scanSettingsService;
    private TargetService targetService;

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }

    @Autowired
    public void setScanSettingsService(ScanSettingsService scanSettingsService) {
        this.scanSettingsService = scanSettingsService;
    }

    @RequestMapping("/list")
    public String listScanSettings(Model model) {
        List<ScanSettings> scanSettingsList = scanSettingsService.showAllScanSettings();
        model.addAttribute("scanSettingsList", scanSettingsList);
        return "scan-settings";
    }

    @RequestMapping(path = "/add")
    public String showScanSettings(Model model, @RequestParam int id) {
        Target target = targetService.findTargetById((long) id);
        if (target == null) return "redirect:/target/list";
        ScanSettings scanSettings = new ScanSettings();
        scanSettings.setTarget(target);
        scanSettings.setTitle("Сканирование " + target.getTitle());
        scanSettings.setTime("not def");
        scanSettingsService.addScanSettings(scanSettings);
        return "redirect:/scansettings/list";
    }

    @RequestMapping("/remove")
    public String removeScanSettings(@RequestParam int id) {
        scanSettingsService.removeScanSettings(id);
        return "redirect:/scansettings/list";
    }

    @RequestMapping("/edit")
    public String editScanSettings(Model model, @RequestParam int id) {
        ScanSettings scanSettings = scanSettingsService.findScanSettingsById((long) id);
        if (scanSettings == null) return "redirect:/scansettings/list";
        model.addAttribute("scanSettings" , scanSettings);
        return "scansettings-edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveScanSettings(ScanSettings scanSettings) {
        if (scanSettings == null) return "redirect:/scansettings/list";
        scanSettingsService.save(scanSettings);
        return "redirect:/scansettings/list";
    }
}
