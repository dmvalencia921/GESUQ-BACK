package com.uniquindio.agendaespacio.security.service.imp;

import com.uniquindio.agendaespacio.entity.Usuario;
import com.uniquindio.agendaespacio.security.entity.AuthRequest;
import com.uniquindio.agendaespacio.security.entity.AuthResponse;
import com.uniquindio.agendaespacio.security.entity.UsuarioAuthorizationDto;
import com.uniquindio.agendaespacio.security.service.IAuthenticationService;
import com.uniquindio.agendaespacio.security.service.IJwtService;
import com.uniquindio.agendaespacio.service.imp.UsuarioService;
import com.uniquindio.agendaespacio.util.constants.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtService tokenProvider;

    @Override
    public AuthResponse login(AuthRequest request) {
        // Aqui se llama a @Bean public AuthenticationProvider en securityConfiguration.
        // En este caso es MphUserDetailService
        Authentication au = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String jwt = tokenProvider.createToken(au);
        UsuarioAuthorizationDto userDTO = infoUsuario(request.getUsername());

        List<String> roles = au.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return AuthResponse.builder().id(userDTO.getId()).userName(request.getUsername()).roles(roles).token(jwt)
                .isAdmin(userDTO.getIsAdmin()).build();
    }

    @Override
    public UsuarioAuthorizationDto infoUsuario(String username) {
        Usuario usuarioExistente = usuarioService.buscarUsuarioPorUsuario(username);
        List<String> roles = usuarioExistente.getListaRol().stream()
                .map((usuarioRol) -> usuarioRol.getNombre()).collect(Collectors.toList());

        boolean isAdmin = roles.contains(Constants.ADMIN_ROLE);

        UsuarioAuthorizationDto usuarioResp = UsuarioAuthorizationDto.builder().username(username).roles(roles)
                .id(usuarioExistente.getIdUsuario()).isAdmin(isAdmin).build();
        return usuarioResp;
    }
}
