package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.ConviteDTO;
import ifsul.io.IFMeet.controller.dto.RegistrarUsuarioDTO;
import ifsul.io.IFMeet.controller.dto.UsuarioDTO;
import ifsul.io.IFMeet.domain.Convite;
import ifsul.io.IFMeet.domain.Usuario;
import ifsul.io.IFMeet.mapper.ConviteMapper;
import ifsul.io.IFMeet.mapper.RegistrarUsuarioMapper;
import ifsul.io.IFMeet.mapper.UsuarioMapper;
import ifsul.io.IFMeet.repository.UsuarioRepository;
import ifsul.io.IFMeet.security.SecurityUtils;
import ifsul.io.IFMeet.service.ConviteService;
import ifsul.io.IFMeet.service.UsuarioService;
import ifsul.io.IFMeet.service.impl.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/v1/registrarUsuario")
public class RegistrarController {

    @Autowired
    RegistrarUsuarioMapper registrarUsuarioMapper;
    @Autowired
    UsuarioMapper usuarioMapper;
    @Autowired
    ConviteMapper conviteMapper;
    @Autowired
    ConviteService conviteService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @ApiOperation(value = "Registra Orientandos", notes = "Faz cadastro de novos Orientandos")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarOrientando(@RequestBody RegistrarUsuarioDTO usuarioDTO) {
        log.debug("entrou registrarUsuario");

        Usuario usuario = registrarUsuarioMapper.toEntity(usuarioDTO);
        usuarioService.registrarOrientando(usuario);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Registra Orientador", notes = "Faz cadastro de novos Orientador")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/registrarOrientador",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarOrientador(@RequestBody RegistrarUsuarioDTO usuarioDTO) {
        log.debug("entrou registrarOrientador");
        Usuario usuario = registrarUsuarioMapper.toEntity(usuarioDTO);
        usuarioService.registrarOrientador(usuario);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Registra Admin", notes = "Faz cadastro de novos administradores")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/registrarAdmin", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarAdmin(@RequestBody RegistrarUsuarioDTO usuarioDTO) {
        log.debug("entrou registrarAdmin");
        Usuario usuario = registrarUsuarioMapper.toEntity(usuarioDTO);
        usuarioService.registrarAdmin(usuario);
        return ResponseEntity.ok().build();
    }
    @ApiOperation(value = "Envia convite", notes = "Valida os dados e envia convite para o email")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @PostMapping("/enviarConvite")
    public ResponseEntity<?> enviarConvite(@Validated @RequestBody ConviteDTO conviteDTO) {
        log.debug("entrou enviarConviteController");
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
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(userDetails.getId());
        Usuario usuario = usuarioOpt.orElse(null);
        return usuario;
    }
}
