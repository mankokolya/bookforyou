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

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private UserService userService;

    //trims empty input and replace it with null
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") userDTO userDTO,
                                      BindingResult bindingResult) {
        if (userService.userExists(userDTO.getEmail())) {
            bindingResult.addError(new FieldError("user", "email",
                    //todo localize error message
                    "Email address already in use"));
            return "registration";
        }

        if (bindingResult.hasErrors()){
            return "registration";
        }

        if(userDTO.getPassword() != null && userDTO.getConfirmPassword() != null) {
            if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                bindingResult.addError(new FieldError("user", "confirmPassword",
                        "Password must match"));
            }
        }
        userService.saveUser(userDTO);
        return "redirect:/login?success";
    }

    @GetMapping
    public String  showRegistrationForm(Model model) {
        model.addAttribute("user", new userDTO());
        return "registration";
    }
}