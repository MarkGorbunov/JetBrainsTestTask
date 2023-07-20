package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Role {
    RolesModel roleId;
    String scope;
}
