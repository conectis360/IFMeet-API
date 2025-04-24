package ifsul.io.IFMeet.api.usuario.controller;

import ifsul.io.IFMeet.api.convite.dto.ConviteDTO;
import ifsul.io.IFMeet.api.usuario.dto.RegistrarUsuarioDTO;
import ifsul.io.IFMeet.api.usuario.mapper.ConviteMapper;
import ifsul.io.IFMeet.api.usuario.mapper.RegistrarUsuarioMapper;
import ifsul.io.IFMeet.domain.convite.model.Convite;
import ifsul.io.IFMeet.domain.convite.service.ConviteService;
import ifsul.io.IFMeet.domain.usuario.model.User;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import ifsul.io.IFMeet.domain.usuario.repository.UsuarioRepository;
import ifsul.io.IFMeet.domain.usuario.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/v1/registrarUsuario")
public class RegistrarController {

    private final RegistrarUsuarioMapper registrarUsuarioMapper;
    private final ConviteMapper conviteMapper;
    private final ConviteService conviteService;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @ApiOperation(value = "Registra usuario", notes = "Faz cadastro de novos usuarios")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody RegistrarUsuarioDTO usuarioDTO) {
        log.debug("into save");
        Usuario usuario = registrarUsuarioMapper.toEntity(usuarioDTO);
        usuarioService.registrarUsuario(usuario, usuarioDTO.getTipoUsuario());

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Envia convite", notes = "Valida os dados e envia convite para o email")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PostMapping("/enviarConvite")
    public ResponseEntity<?> enviarConvite(@Validated @RequestBody ConviteDTO conviteDTO) {
        log.debug("entrou enviarConvite");
        Convite convite = conviteMapper.toEntity(conviteDTO);
        convite.setUsuarioResponsavel(this.retornaUsuario());
        conviteService.enviarConvite(convite);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/verificarCredenciais/{codigo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ConviteDTO> verificarCredenciais(@PathVariable String codigo) {
        log.debug("entrou verificarCredenciais");

        Convite convite = conviteService.findCredenciais(codigo);
        ConviteDTO conviteDTO = conviteMapper.toDto(convite);

        return ResponseEntity.ok(conviteDTO);
    }

    public Usuario retornaUsuario() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(userDetails.getId());
        Usuario usuario = usuarioOpt.orElse(null);
        return usuario;
    }
}
