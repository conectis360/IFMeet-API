package ifsul.io.IFMeet.domain.usuario.service;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.api.usuario.dto.LoginDTO;
import ifsul.io.IFMeet.domain.usuario.model.User;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.payload.response.JwtResponse;
import ifsul.io.IFMeet.domain.usuario.repository.UsuarioRepository;
import ifsul.io.IFMeet.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository userRepository;
    private final JwtUtils jwtUtils;
    private final Messages messages;

    private Authentication autenticaUsuario(String username, String password) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new BusinessException(messages.get("login.usuario-senha-invalido"));
        }
        return authentication;
    }

    public JwtResponse fazLogin(LoginDTO loginDTO) {
        Authentication autenticado = this.autenticaUsuario(loginDTO.getUsername(), loginDTO.getPassword());

        SecurityContextHolder.getContext().setAuthentication(autenticado);
        String jwt = jwtUtils.generateJwtToken(autenticado);

        User userDetails = (User) autenticado.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        JwtResponse token = new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                userDetails.getNome()
        );

        return token;
    }
}
