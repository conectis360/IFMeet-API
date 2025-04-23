package ifsul.io.IFMeet.domain.usuario.service;

import ifsul.io.IFMeet.api.usuario.dto.UsuarioDTO;
import ifsul.io.IFMeet.api.usuario.dto.UsuarioFilterDto;
import ifsul.io.IFMeet.api.usuario.mapper.UsuarioMapper;
import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.config.Role;
import ifsul.io.IFMeet.domain.usuario.enums.TipoUsuarioEnum;
import ifsul.io.IFMeet.domain.usuario.model.TipoUsuario;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.domain.usuario.repository.TipoUsuarioRepository;
import ifsul.io.IFMeet.domain.usuario.repository.UsuarioRepository;
import ifsul.io.IFMeet.domain.usuario.repository.UsuarioSpecs;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import ifsul.io.IFMeet.security.SecurityUtils;
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

    public void registrarUsuario(Usuario usuario, TipoUsuarioEnum tipoUsuario) {
        log.debug("into registrarUsuario method");
        this.retornaUsuarioRegistrado(usuario);
        Usuario user = new Usuario(usuario.getUsuario(), usuario.getEmail(), encoder.encode(usuario.getSenha()));

        switch (tipoUsuario) {
            case ORIENTADOR:
                user.setTipoUsuario(this.devolvePermissoes(Role.ROLE_ORIENTADOR));
                break;
            case ORIENTANDO:
                user.setTipoUsuario(this.devolvePermissoes(Role.ROLE_ORIENTANDO));
                break;
            case ADMIN:
                user.setTipoUsuario(this.devolvePermissoes(Role.ROLE_ADMIN));
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuário inválido");
        }

        usuarioRepository.save(user);
    }


    private Set<TipoUsuario> devolvePermissoes(Role role) {
        log.debug("into devolvePermissoes method");
        Set<TipoUsuario> roles = new HashSet<>();

        TipoUsuario userRole = tipoUsuarioRepository.findByTipoUsuario(role)
                .orElseThrow(() -> new RuntimeException(messages.get("permissoes.permissao-inexistente")));

        roles.add(userRole);
        return roles;
    }

    /**
     * Verifica se o usuário possui uma determinada role.
     *
     * @param usuario O usuário a ser verificado
     * @param role A role a ser verificada
     * @return true se o usuário possui a role especificada, false caso contrário
     */
    private static boolean possuiRole(Usuario usuario, Role role) {
        if (usuario == null || usuario.getTipoUsuario() == null) {
            return false;
        }

        return usuario.getTipoUsuario().stream()
                .map(TipoUsuario::getTipoUsuario)
                .anyMatch(tipoRole -> tipoRole == role);
    }

    public static boolean possuiRoleStatic(Usuario usuario, Role role) {
        if (usuario == null || usuario.getTipoUsuario() == null) {
            return false;
        }

        return usuario.getTipoUsuario().stream()
                .map(TipoUsuario::getTipoUsuario)
                .anyMatch(tipoRole -> tipoRole == role);
    }

}
