package ru.zakharov.samboss.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.zakharov.samboss.entities.Role;
import ru.zakharov.samboss.entities.SystemUser;
import ru.zakharov.samboss.entities.User;
import ru.zakharov.samboss.services.UserService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model model) {
        Collection<Role> roles = userService.findAllRoles();
        model.addAttribute("systemUser", new SystemUser());
        model.addAttribute("roles", roles);
        return "registration-form";
    }

    // Binding Result после @ValidModel !!!
    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("systemUser") SystemUser theSystemUser,
                                          BindingResult theBindingResult,
                                          Model model) {
        String userName = theSystemUser.getUserName();
        logger.debug("Processing registration form for: " + userName);
        if (theBindingResult.hasErrors()) {
            Collection<Role> roles = userService.findAllRoles();
            model.addAttribute("roles", roles);
            return "registration-form";
        }
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            Collection<Role> roles = userService.findAllRoles();
            model.addAttribute("systemUser", new SystemUser());
            model.addAttribute("registrationError", "User name already exists.");
            model.addAttribute("roles", roles);
            logger.debug("User name already exists.");
            return "registration-form";
        }
        userService.save(theSystemUser);
        logger.debug("Successfully created user: " + userName);
        return "registration-confirmation";
    }

    @RequestMapping("/showAllUsers")
    public String showAllUsers(Model model) {
        Collection<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }
}
