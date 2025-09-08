package com.uniquindio.agendaespacio.rescontroller;

import com.uniquindio.agendaespacio.security.entity.AuthRequest;
import com.uniquindio.agendaespacio.security.entity.AuthResponse;
import com.uniquindio.agendaespacio.security.entity.UsuarioAuthorizationDto;
import com.uniquindio.agendaespacio.security.service.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/security")
@RequiredArgsConstructor
public class AuhtenticateRestController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/authenticate")
    private ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authenticationService.login(authRequest));
    }

    @GetMapping("/info")
    private ResponseEntity<UsuarioAuthorizationDto> info(Principal principal) {
        return ResponseEntity.ok(authenticationService.infoUsuario(principal.getName()));
    }

}
