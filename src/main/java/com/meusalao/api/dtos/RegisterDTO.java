package com.meusalao.api.dtos;

import com.meusalao.api.utils.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
  
}


