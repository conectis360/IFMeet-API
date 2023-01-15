package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.TCCDTO;
import ifsul.io.IFMeet.domain.TCC;
import ifsul.io.IFMeet.mapper.TCCMapper;
import ifsul.io.IFMeet.service.TCCService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/tcc")
public class TCCController {
    private final TCCService tccService;

    private final TCCMapper tccMapper;
    @Autowired

    @GetMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TCCDTO>> findAll() {
        List<TCCDTO> listaDto = new ArrayList<>();
        List<TCC> lista = tccService.findAll();

        for(TCC tcc : lista) {
            TCCDTO dto = tccMapper.toDto(tcc);
            listaDto.add(dto);
        }

        return ResponseEntity.ok(listaDto);
    }

    @GetMapping(value = "/{comentario}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TCCDTO> findById(
            @PathVariable String comentario
    ) {
        Optional<TCC> tcc = tccService.findByComentario(comentario);
        TCC tcc1 = tcc.orElseThrow(() -> new EmptyResultDataAccessException(1));
        TCCDTO dto = tccMapper.toDto(tcc1);

        return ResponseEntity.ok(dto);
    }

}
