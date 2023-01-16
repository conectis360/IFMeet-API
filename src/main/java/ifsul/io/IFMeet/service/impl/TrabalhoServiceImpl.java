package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Trabalho;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.repository.TrabalhoRepository;
import ifsul.io.IFMeet.service.TrabalhoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrabalhoServiceImpl implements TrabalhoService {

    @Autowired
    TrabalhoRepository trabalhoRepository;
    @Autowired
    Messages messages;

    public Trabalho findTrabalhoById(Long codigoTrabalho) {
        log.debug("into findTrabalhoById method");
        Optional<Trabalho> trabalhoOpt = trabalhoRepository.findById(codigoTrabalho);
        Trabalho trabalho = trabalhoOpt.orElse(null);
        return trabalho;
    }

    public List<Trabalho> findAllTrabalhos() {
        log.debug("into findAllTrabalhos method");
        List<Trabalho> listaTrabalhos = trabalhoRepository.findAll();
        return listaTrabalhos;
    }

    public void save (Trabalho trabalho) {
        log.debug("into save method");
        this.validaOrientando(trabalho.getAluno().getId());
        trabalhoRepository.save(trabalho);
    }

    private void validaOrientando(Long codigoOrientando) {
        log.debug("into validaOrientando method");
        Trabalho trabalho = this.findTrabalhoById(codigoOrientando);
        if(trabalho != null) {
            throw new BusinessException(messages.get("trabalho.orientando-ja-possui-trabalho"));
        }
    }

}
