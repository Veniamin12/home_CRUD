package com.example.home_crud.Controller;

import jakarta.validation.Valid;
import com.example.home_crud.DTO.UserDto;
import com.example.home_crud.Model.User;
import com.example.home_crud.Service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        log.info("Received request to create object with data: {}", userDto);
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
            log.warn("User is already exist");
        }
        if (result.hasErrors()) {
            log.warn("Validation errors occurred during object creation: {}", result.getAllErrors());
            model.addAttribute("user", userDto);
            return "/register";
        }
        log.debug("Object successfully created: {}", userDto);
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        log.debug("Received all registered user!");
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}

