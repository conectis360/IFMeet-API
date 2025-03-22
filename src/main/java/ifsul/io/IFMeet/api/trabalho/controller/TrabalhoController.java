package ifsul.io.IFMeet.api.trabalho.controller;

import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoDTO;
import ifsul.io.IFMeet.api.trabalho.dto.TrabalhoFilterDto;
import ifsul.io.IFMeet.api.trabalho.mapper.TrabalhoMapper;
import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import ifsul.io.IFMeet.domain.trabalho.service.TrabalhoService;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/trabalho")
public class TrabalhoController {

    private final TrabalhoMapper trabalhoMapper;
    private final TrabalhoService trabalhoService;

    @ApiOperation(value = "Retornar todos trabalhos", notes = "Retornar todos trabalhos")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DefaultPaginationResponse<TrabalhoDTO> findAll(@ParameterObject DefaultRequestParams request, @ParameterObject TrabalhoFilterDto trabalhoFilterDto) {
        log.debug("findAll");
        return trabalhoService.findAll(request, trabalhoFilterDto);
    }

    @ApiOperation(value = "Salva trabalho", notes = "Salva um trabalho")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody TrabalhoDTO trabalhoDTO) {
        log.debug("into save");
        Trabalho trabalho = trabalhoMapper.toEntity(trabalhoDTO);
        trabalhoService.save(trabalho);
        return ResponseEntity.ok().build();
    }
}
