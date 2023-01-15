package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.ConviteDTO;
import ifsul.io.IFMeet.controller.dto.RegistrarUsuarioDTO;
import ifsul.io.IFMeet.domain.Convite;
import ifsul.io.IFMeet.domain.Usuario;
import ifsul.io.IFMeet.mapper.ConviteMapper;
import ifsul.io.IFMeet.mapper.RegistrarUsuarioMapper;
import ifsul.io.IFMeet.service.ConviteService;
import ifsul.io.IFMeet.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/v1/registrarUsuario")
public class RegistrarController {

    @Autowired
    RegistrarUsuarioMapper registrarUsuarioMapper;
    @Autowired
    ConviteMapper conviteMapper;
    @Autowired
    ConviteService conviteService;
    @Autowired
    UsuarioService usuarioService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarOrientando(@RequestBody RegistrarUsuarioDTO usuarioDTO) {
        log.debug("entrou registrarUsuario");
        Usuario usuario = registrarUsuarioMapper.toEntity(usuarioDTO);
        usuarioService.registrarOrientando(usuario);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/registrarOrientador",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarOrientador(@RequestBody RegistrarUsuarioDTO usuarioDTO) {
        log.debug("entrou registrarOrientador");
        Usuario usuario = registrarUsuarioMapper.toEntity(usuarioDTO);
        usuarioService.registrarOrientador(usuario);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/registrarAdmin",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarAdmin(@RequestBody RegistrarUsuarioDTO usuarioDTO) {
        log.debug("entrou registrarAdmin");
        Usuario usuario = registrarUsuarioMapper.toEntity(usuarioDTO);
        usuarioService.registrarAdmin(usuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/enviarConvite")
    public ResponseEntity<?> enviarConvite(@Validated @RequestBody ConviteDTO conviteDTO) {
        log.debug("entrou enviarConviteController");
        Convite convite = conviteMapper.toEntity(conviteDTO);
        conviteService.enviarConvite(convite);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verificarCredenciais/{codigo}")
    public ResponseEntity<ConviteDTO> verificarCredenciais(@PathVariable String codigo) {
        log.debug("entrou verificarCredenciais");

        Convite convite = conviteService.findCredenciais(codigo);
        ConviteDTO conviteDTO = conviteMapper.toDto(convite);

        return ResponseEntity.ok(conviteDTO);
    }
}
