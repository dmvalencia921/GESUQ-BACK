package com.uniquindio.agendaespacio.security.service;

import com.uniquindio.agendaespacio.security.entity.AuthRequest;
import com.uniquindio.agendaespacio.security.entity.AuthResponse;
import com.uniquindio.agendaespacio.security.entity.UsuarioAuthorizationDto;

public interface IAuthenticationService {
    AuthResponse login(AuthRequest request);
    UsuarioAuthorizationDto infoUsuario(String username);
}
