package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Trabalho;
import ifsul.io.IFMeet.domain.Usuario;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.repository.TrabalhoRepository;
import ifsul.io.IFMeet.security.SecurityUtils;
import ifsul.io.IFMeet.service.TrabalhoService;
import ifsul.io.IFMeet.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrabalhoServiceImpl implements TrabalhoService {

    @Autowired
    TrabalhoRepository trabalhoRepository;
    @Autowired
    UsuarioService usuarioService;
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

    public List<Trabalho> findAllByOrientador() {
        log.debug("into findAllByOrientador method");
        Usuario orientador = usuarioService.retornarUsuarioLogado(SecurityUtils.getCurrentUserLogin().orElseThrow(()-> new BusinessException(messages.get("usuario.nao-encontrado"))));
        List<Trabalho> listaTrabalhos = trabalhoRepository.findAllByOrientadorId(orientador.getId());
        return listaTrabalhos;
    }

    @Transactional
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
