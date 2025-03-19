package com.uniquindio.agendaespacio.security.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsuarioAuthorizationDto {
    String username;
    private List<String> roles;
    Integer id;
    Boolean isAdmin;
}
