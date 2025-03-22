package ifsul.io.IFMeet.api.usuario.controller;

import ifsul.io.IFMeet.api.usuario.dto.UsuarioDTO;
import ifsul.io.IFMeet.api.usuario.dto.UsuarioFilterDto;
import ifsul.io.IFMeet.domain.usuario.service.UsuarioService;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @ApiOperation(value = "Retornar todos usuarios", notes = "Retornar todos usuarios")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DefaultPaginationResponse<UsuarioDTO> findAll(@ParameterObject DefaultRequestParams request, @ParameterObject UsuarioFilterDto usuarioFilterDto) {
        log.debug("findAll");
        return usuarioService.findAll(request, usuarioFilterDto);
    }
}
