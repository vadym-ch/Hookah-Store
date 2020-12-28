package com.kpi.internetshop.controller;

import com.kpi.internetshop.mapper.UserMapper;
import com.kpi.internetshop.service.RoleService;
import com.kpi.internetshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Collectors;

@Controller
public class UserController {
    private UserService userService;
    private UserMapper userMapper;
    private RoleService roleService;

    public UserController(UserService userService, UserMapper userMapper, RoleService roleService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getAll(Model model) {
        model.addAttribute("listOfUsers", userService.getAllByRole(roleService.getRoleByName("USER")).stream()
        .map(user -> userMapper.mapFromUserToUserResponseDto(user)).collect(Collectors.toList()));
        return "allUsers";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable (value = "id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
