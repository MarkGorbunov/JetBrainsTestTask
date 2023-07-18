package com.models;

import lombok.Data;

@Data
public class User {
    String userName;
    String password;
    String role;
    String token;
}
