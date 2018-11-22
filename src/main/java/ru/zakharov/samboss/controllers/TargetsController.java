package ru.zakharov.samboss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zakharov.samboss.entities.Target;
import ru.zakharov.samboss.services.TargetService;


import java.util.List;

@Controller
@RequestMapping("/target")
public class TargetsController {
    private TargetService targetService;

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }

    @RequestMapping("/list")
    public String listAllTargets(Model model) {
        List<Target> targetList = targetService.showAllTargets();
        model.addAttribute("targetList", targetList);
        Target target = new Target();
        model.addAttribute("target", target);
        return "targets";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String showTargetList(Target target) {
        targetService.addTarget(target);
        return "redirect:/target/list";
    }

    @RequestMapping("/remove")
    public String removeTargetById(@RequestParam int id) {
        targetService.removeTargetById(id);
        return "redirect:/target/list";
    }

    @RequestMapping("/edit")
    public String editTarget(Model model, @RequestParam int id) {
        Target target = targetService.findTargetById((long) id);
        if (target == null) return "redirect:/target/list";
        model.addAttribute("target", target);
        return "target-edit.html";
    }
}
