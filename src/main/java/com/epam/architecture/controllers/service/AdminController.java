package com.epam.architecture.controllers.service;

import com.epam.architecture.controllers.dto.UserDTO;
import com.epam.architecture.service.AccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(AccountService accountService, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user")
    public boolean addNewUser(@RequestBody UserDTO user) {
        return accountService.addAccount(user.getLogin(), passwordEncoder.encode(user.getPassword()));
    }

    @DeleteMapping("/user/{login}")
    public void banUser(@PathVariable String login) {
        accountService.deleteUser(login);
    }

    @GetMapping("/history")
    public String takeHistory() {
        return accountService.takeHistory();
    }
}
