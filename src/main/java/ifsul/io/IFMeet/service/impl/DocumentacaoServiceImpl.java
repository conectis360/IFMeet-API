package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Documentacao;
import ifsul.io.IFMeet.repository.CursoRepository;
import ifsul.io.IFMeet.repository.DocumentacaoRepository;
import ifsul.io.IFMeet.service.CursoService;
import ifsul.io.IFMeet.service.DocumentacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentacaoServiceImpl implements DocumentacaoService {

    @Autowired
    DocumentacaoRepository documentacaoRepository;

    @Autowired
    Messages messages;

    public Optional<Documentacao> findById(Long id) {
        return documentacaoRepository.findById(id);
    }

    public List<Documentacao> findAll() {
        return null;
    }

    public void save(Documentacao documentacao) {
    }

}
