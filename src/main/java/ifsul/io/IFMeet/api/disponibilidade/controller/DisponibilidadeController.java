package ifsul.io.IFMeet.api.disponibilidade.controller;

import ifsul.io.IFMeet.api.disponibilidade.dto.DisponibilidadeDTO;
import ifsul.io.IFMeet.api.disponibilidade.dto.DisponibilidadeFilterDto;
import ifsul.io.IFMeet.domain.disponibilidade.service.DisponibilidadeService;
import ifsul.io.IFMeet.payload.response.DefaultPaginationResponse;
import ifsul.io.IFMeet.payload.response.DefaultRequestParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/v1/disponibilidade")
public class DisponibilidadeController {
    private final DisponibilidadeService disponibilidadeService;


    @ApiOperation(value = "Retornar todas Reuniões", notes = "Retornar todas Reuniões")
    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DefaultPaginationResponse<DisponibilidadeDTO> findAll(@ParameterObject DefaultRequestParams request, @ParameterObject DisponibilidadeFilterDto disponibilidadeFilterDto) {
        log.debug("findAll");
        return disponibilidadeService.findAll(request, disponibilidadeFilterDto);
    }
}
