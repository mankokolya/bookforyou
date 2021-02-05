package com.library.bookforyou.controllers;

import com.library.bookforyou.services.UserService;
import com.library.bookforyou.web.dto.userDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") userDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!Objects.equals(userDTO.getPassword(), userDTO.getConfirmPassword())) {
            bindingResult.addError(new FieldError("user", "confirmPassword",
                    "Password must match"));
            return "registration";
        }

        if (userService.userExists(userDTO.getEmail())) {
            bindingResult.addError(new FieldError("user", "email",
                    //todo localize error message
                    "Email address already in use"));
            return "registration";
        }

        userService.saveUser(userDTO);
        return "redirect:/login?success";
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new userDTO());
        return "registration";
    }
}