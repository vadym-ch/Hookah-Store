package com.kpi.internetshop.controller;

import com.kpi.internetshop.entity.dto.request.UserRequestDto;
import com.kpi.internetshop.security.AuthenticationService;
import com.kpi.internetshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserService userService;
    private AuthenticationService authenticationService;

    public RegistrationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @ModelAttribute("user")
    public UserRequestDto getDto() {
        return new UserRequestDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") UserRequestDto user, BindingResult bindingResult,
            ModelMap modelMap, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("successMessage", "Please correct errors in the form!");
            modelMap.addAttribute("bindingResult", bindingResult);
            return "registration";
        } else if (!userService.isUniqueUsername(user.getEmail())) {
            model.addAttribute("successMessage", "Your email has to be unique! User with this email already exists!");
            return "registration";
        } else {
            authenticationService.register(user.getFirstName(), user.getLastName(), user.getEmail(),
                    user.getPassword());
            return "redirect:/registration?success";
        }
    }
}
