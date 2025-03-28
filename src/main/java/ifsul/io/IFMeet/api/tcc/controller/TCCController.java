package ifsul.io.IFMeet.api.tcc.controller;

import ifsul.io.IFMeet.api.tcc.dto.TCCDTO;
import ifsul.io.IFMeet.domain.tcc.model.TCC;
import ifsul.io.IFMeet.api.tcc.mapper.TCCMapper;
import ifsul.io.IFMeet.domain.tcc.service.TCCService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
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
