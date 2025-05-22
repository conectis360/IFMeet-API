package ifsul.io.IFMeet.api.dashboard.controller;

import ifsul.io.IFMeet.api.dashboard.dto.DashboardDto;
import ifsul.io.IFMeet.api.dashboard.mapper.DashboardMapper;
import ifsul.io.IFMeet.domain.ata.model.Ata;
import ifsul.io.IFMeet.domain.ata.service.AtaService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final AtaService ataService;
    private final DashboardMapper dashboardMapper;

/*
    @ApiOperation(value = "Retornar todas atas", notes = "Retornar todas atas")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DefaultPaginationResponse<DashboardDto> findAll(
            @ParameterObject DefaultRequestParams request,
            @ParameterObject DashboardFiltersDto ataFilterDto) {
        log.debug("into findAll");
        return ataService.findAll(request, ataFilterDto);
        }
 */

    @ApiOperation(value = "Retornar Ata por ID", notes = "Retorna Ata por ID")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DashboardDto> findById(@PathVariable("id") Long id) {
        log.debug("into findById");
        Optional<Ata> ataOptional = ataService.findById(id);
        Ata ata = ataOptional.orElse(null);
        DashboardDto dashboardDto = dashboardMapper.toDto(ata);
        return ResponseEntity.ok(dashboardDto);
    }

    @ApiOperation(value = "Salva Ata", notes = "Salva uma Ata")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> save(@RequestBody DashboardDto dashboardDto) {
        log.debug("into save");
        Ata ata = dashboardMapper.toEntity(dashboardDto);
        ataService.save(ata);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update Ata", notes = "Atualiza uma Ata")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ORIENTADOR') or hasRole('ROLE_ORIENTANDO')")
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> update(@RequestBody DashboardDto dashboardDto) {
        log.debug("into save");
        Ata ata = dashboardMapper.toEntity(dashboardDto);
        ataService.update(ata);
        return ResponseEntity.ok().build();
    }


}
