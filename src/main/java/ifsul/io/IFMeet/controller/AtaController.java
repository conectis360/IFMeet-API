package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.AtaDTO;
import ifsul.io.IFMeet.domain.Ata;
import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.mapper.AtaMapper;
import ifsul.io.IFMeet.service.AtaService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/ata")
@CrossOrigin(origins = "*")
public class AtaController {

    @Autowired
    AtaService ataService;
    @Autowired
    AtaMapper ataMapper;

    @ApiOperation(value = "Buscar todas as Atas", notes = "Retorna todas as atas cadastradas no banco de dados")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AtaDTO>> findAll() {
        log.debug("entrou findAll");
        List<AtaDTO> listaAtaDTO = new ArrayList<>();
        List<Ata> listaAta = ataService.findAll();

        for (Ata ata : listaAta) {
            listaAtaDTO.add(ataMapper.toDto(ata));
        }

        return ResponseEntity.ok(listaAtaDTO);
    }

    @ApiOperation(value = "Retornar Atas por ID", notes = "Retorna Ata por ID")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AtaDTO> findById(@PathVariable("id") Long id) {
        log.debug("entrou registrarOrientador");
         Optional<Ata> ataOptional = ataService.findById(id);
        Ata ata = ataOptional.orElse(null);
        AtaDTO ataDTO = ataMapper.toDto(ata);
        return ResponseEntity.ok(ataDTO);
    }

    @ApiOperation(value = "Cadastrar Ata", notes = "Cadastra uma nova Ata")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COORDENADOR')")
    @PostMapping(value = "/cadastrarCurso", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> registrarCurso(@RequestBody AtaDTO ataDTO) {
        log.debug("entrou registrarOrientador");
        Ata ata = ataMapper.toEntity(ataDTO);
        ataService.save(ata);
        return ResponseEntity.ok().build();
    }
}
