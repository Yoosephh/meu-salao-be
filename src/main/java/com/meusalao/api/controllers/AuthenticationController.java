package com.meusalao.api.controllers;

import org.springframework.web.bind.annotation.*;

import com.meusalao.api.dtos.RegisterDTO;
import com.meusalao.api.dtos.SigninDTO;
import com.meusalao.api.models.User;
import com.meusalao.api.repositories.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
  @Autowired
  private UserRepository repository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data) {
      if(this.repository.findByEmail(data.login()) != null) return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use.");

      String encryptedPass = encoder.encode(data.password());
      User newUser = new User(data.login(), encryptedPass, data.role());

      this.repository.save(newUser);
      
      return ResponseEntity.ok().build();
  }
  
  @PostMapping("/signin")
  public ResponseEntity<String> signin(@RequestBody @Valid SigninDTO data) {
    User user = this.repository.findByEmail(data.login());

    if(user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found.");

    if(!encoder.matches(data.password(), user.getPassword())) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password.");

    return ResponseEntity.status(HttpStatus.CREATED).body("User registered.");
  }
}
