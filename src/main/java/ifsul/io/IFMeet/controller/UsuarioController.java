package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.UsuarioDTO;
import ifsul.io.IFMeet.domain.Roles;
import ifsul.io.IFMeet.domain.Usuario;
import ifsul.io.IFMeet.mapper.UsuarioMapper;
import ifsul.io.IFMeet.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioMapper usuarioMapper;
    @Autowired
    UsuarioService usuarioService;


    @ApiOperation(value = "Retorna Alunos", notes = "Retorna lista de Alunos")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/findAllAlunos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UsuarioDTO>> retornarAlunos() {
        log.debug("entrou retornarAlunos");
        List<Roles> usuarios = usuarioService.retornarAlunos();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Roles role : usuarios) {
            usuariosDTO.add(usuarioMapper.toDto(role.getUsuario()));
        }
        return ResponseEntity.ok(usuariosDTO);
    }
    @ApiOperation(value = "Retorna Orientadores", notes = "Retorna lista de Orientadores")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/findAllOrientadores", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UsuarioDTO>> retornarOrientadores() {
        log.debug("entrou retornarOrientadores");
        List<Roles> usuarios = usuarioService.retornarOrientadores();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Roles role : usuarios) {
            usuariosDTO.add(usuarioMapper.toDto(role.getUsuario()));
        }
        return ResponseEntity.ok(usuariosDTO);
    }
}
