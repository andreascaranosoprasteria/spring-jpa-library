package com.corsojava.library.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.library.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@PostMapping("/checkpassword")
	public ResponseEntity<Object> checkPassword(@Valid @RequestBody User user) {
		boolean isValid = false;
		char c;
		try {
			if (user.getPassword().length() < 6) {
				isValid = false;

			} else {
				for (int i = 0; i < user.getPassword().length(); i++) {
					c = user.getPassword().charAt(i);
					if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')) {

						isValid = true;
					} else {
						isValid = false;
					}

				}

			}
			if (isValid) {
				return new ResponseEntity<Object>("valid password", HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("invalid password", HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			return new ResponseEntity<Object>("invalid password", HttpStatus.FORBIDDEN);
		}

	}

}
