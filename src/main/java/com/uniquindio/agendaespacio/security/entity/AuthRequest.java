package com.uniquindio.agendaespacio.security.entity;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
