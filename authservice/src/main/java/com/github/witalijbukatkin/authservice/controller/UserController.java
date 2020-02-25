package com.github.witalijbukatkin.authservice.controller;

import com.github.witalijbukatkin.authservice.model.User;
import com.github.witalijbukatkin.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/oauth")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/current")
	public Principal getCurrent(Principal principal) {
		return principal;
	}

	@PostMapping("/register")
	public User register(@RequestBody User user){
		return userService.register(user);
	}
}
