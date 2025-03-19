package com.meusalao.api.utils;

public enum UserRole {
  
  GARCOM("garcom"),
  ADMIN("admin"),
  CAIXA("caixa"),
  COZINHA("cozinha");

  private String role;

  UserRole(String role) {
    this.role = role;
  }

  public String getRole(){
    return role;
  }
}

