package ifsul.io.IFMeet.domain.usuario.service;

import ifsul.io.IFMeet.api.usuario.dto.UsuarioDTO;
import ifsul.io.IFMeet.api.usuario.dto.UsuarioFilterDto;
import ifsul.io.IFMeet.api.usuario.mapper.UsuarioMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.config.Role;
import ifsul.io.IFMeet.domain.usuario.model.TipoUsuario;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.domain.usuario.repository.TipoUsuarioRepository;
import ifsul.io.IFMeet.domain.usuario.repository.UsuarioRepository;
import ifsul.io.IFMeet.domain.usuario.repository.UsuarioSpecs;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import ifsul.io.IFMeet.utils.PageRequestHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder encoder;
    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final PageRequestHelper pageRequestHelper;
    private final UsuarioMapper usuarioMapper;
    private final Messages messages;


    public DefaultPaginationResponse<UsuarioDTO> findAll(DefaultRequestParams request, UsuarioFilterDto usuarioFilterDto) {
        log.debug("into findAll method");
        Page<UsuarioDTO> pageResult = usuarioRepository
                .findAll(UsuarioSpecs.usuarioFilter(usuarioFilterDto), pageRequestHelper.getPageRequest(request))
                .map(usuarioMapper::toDto);

        List<UsuarioDTO> listaUsuarios = pageResult.getContent();

        return DefaultPaginationResponse.<UsuarioDTO>builder()
                .pageNumber(request.getPageNumber())
                .totalPages(pageResult.getTotalPages())
                .totalRecords(pageResult.getTotalElements())
                .pageSize(pageResult.getContent().size())
                .records(listaUsuarios)
                .build();
    }

    public Usuario retornarUsuarioLogado(String username) {
        log.debug("into retornaUsuarioRegistrado method");
        return usuarioRepository.findByUsuario(username).orElseThrow(() -> new BusinessException(messages.get("usuario.nao-encontrado")));
    }

    private void retornaUsuarioRegistrado(Usuario usuario) {
        log.debug("into retornaUsuarioRegistrado method");
        if (usuarioRepository.existsByUsuario(usuario.getUsuario())) {
            throw new BusinessException(messages.get("usuario.usuario-ja-cadastrado"));
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new BusinessException(messages.get("usuario.email-ja-cadastrado"));
        }
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
