package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.controller.dto.LoginDTO;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.payload.response.JwtResponse;
import ifsul.io.IFMeet.repository.UsuarioRepository;
import ifsul.io.IFMeet.security.JwtUtils;
import ifsul.io.IFMeet.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthServiceImpl  implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    Messages messages;

    private Authentication autenticaUsuario(String username, String password) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (BadCredentialsException e){
            throw new BusinessException(messages.get("login.usuario-senha-invalido"));
        }
        return authentication;
    }

    public JwtResponse fazLogin(LoginDTO loginDTO) {
        Authentication autenticado = this.autenticaUsuario(loginDTO.getUsername(), loginDTO.getPassword());

        SecurityContextHolder.getContext().setAuthentication(autenticado);
        String jwt = jwtUtils.generateJwtToken(autenticado);

        UserDetailsImpl userDetails = (UserDetailsImpl) autenticado.getPrincipal();
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
