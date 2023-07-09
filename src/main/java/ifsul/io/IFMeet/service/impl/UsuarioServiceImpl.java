package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.Config.Role;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Roles;
import ifsul.io.IFMeet.domain.TipoUsuario;
import ifsul.io.IFMeet.domain.Usuario;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.repository.RoleRepository;
import ifsul.io.IFMeet.repository.TipoUsuarioRepository;
import ifsul.io.IFMeet.repository.UsuarioRepository;
import ifsul.io.IFMeet.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    Messages messages;

    private void retornaUsuarioRegistrado(Usuario usuario) {
        log.debug("into retornaUsuarioRegistrado method");
        if (usuarioRepository.existsByUsuario(usuario.getUsuario())) {
            throw new BusinessException(messages.get("usuario.usuario-ja-cadastrado"));
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new BusinessException(messages.get("usuario.email-ja-cadastrado"));
        }
    }

    @Override
    public List<Roles> retornarAlunos() {
        log.debug("into retornarAlunos method");
        List<Roles> listaDeUsuario = roleRepository.findByRoleId(2L);

        return listaDeUsuario;
    }

    public void registrarOrientador(Usuario usuario) {
        log.debug("into registrar method");
        this.retornaUsuarioRegistrado(usuario);
        Usuario user = new Usuario(usuario.getUsuario(), usuario.getEmail(), encoder.encode(usuario.getSenha()));
        user.setTipoUsuario(this.devolvePermissoesOrientando());
        usuarioRepository.save(user);
    }

    public void registrarOrientando(Usuario usuario) {
        log.debug("into registrar method");
        this.retornaUsuarioRegistrado(usuario);
        Usuario user = new Usuario(usuario.getUsuario(), usuario.getEmail(), encoder.encode(usuario.getSenha()));
        user.setTipoUsuario(this.devolvePermissoesOrientando());
        usuarioRepository.save(user);
    }

    public void registrarAdmin(Usuario usuario) {
        log.debug("into registrar method");
        this.retornaUsuarioRegistrado(usuario);
        Usuario user = new Usuario(usuario.getUsuario(), usuario.getEmail(), encoder.encode(usuario.getSenha()));
        user.setTipoUsuario(this.devolvePermissoesOrientando());
        usuarioRepository.save(user);
    }

    private Set<TipoUsuario> devolvePermissoesOrientando() {
        log.debug("into devolvePermissoes method");
        Set<TipoUsuario> roles = new HashSet<>();

        TipoUsuario userRole = tipoUsuarioRepository.findByTipoUsuario(Role.ROLE_ORIENTANDO).orElseThrow(() -> new RuntimeException(messages.get("permissoes.permissao-inexistente")));
        roles.add(userRole);

        return roles;
    }
}
