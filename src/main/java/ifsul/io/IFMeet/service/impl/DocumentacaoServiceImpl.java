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

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentacaoServiceImpl implements DocumentacaoService {

    @Autowired
    DocumentacaoRepository documentacaoRepository;

    @Autowired
    Messages messages;

    public Documentacao findById() {
        return null;
    }

    public List<Documentacao> findAll() {
        return null;
    }

    public void save(Documentacao documentacao) {
    }

}
