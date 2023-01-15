package ifsul.io.IFMeet.controller;

import ifsul.io.IFMeet.controller.dto.TrabalhoDTO;
import ifsul.io.IFMeet.domain.EmailDetails;
import ifsul.io.IFMeet.domain.Trabalho;
import ifsul.io.IFMeet.mapper.TrabalhoMapper;
import ifsul.io.IFMeet.repository.TrabalhoRepository;
import ifsul.io.IFMeet.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/trabalho")
public class TrabalhoController {

    @Autowired
    TrabalhoMapper trabalhoMapper;

    @Autowired
    TrabalhoRepository trabalhoRepository;

    @GetMapping("/teste")
    public void enviarEmailSimples(){
        Optional<Trabalho> trabalhoOpt = trabalhoRepository.findById(Long.parseLong("1"));
        Trabalho trabalho = trabalhoOpt.orElse(null);
        TrabalhoDTO trabalhoDTO = trabalhoMapper.toDto(trabalho);
        System.out.println(trabalhoDTO);
    }
}
