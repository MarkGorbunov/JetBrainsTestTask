package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

//todo here we have only one argument; i thing @AllArgsConstructor is not necessary
@AllArgsConstructor
@Data
public class Roles {
    private List<Role> role;
}
