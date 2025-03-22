package ifsul.io.IFMeet.domain.documentacao.service;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.documentacao.model.Documentacao;
import ifsul.io.IFMeet.domain.documentacao.repository.DocumentacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentacaoService {

    private final DocumentacaoRepository documentacaoRepository;

    private final Messages messages;

    public Optional<Documentacao> findById(Long id) {
        return documentacaoRepository.findById(id);
    }

    public List<Documentacao> findAll() {
        return null;
    }

    public void save(Documentacao documentacao) {
        documentacaoRepository.save(documentacao);
    }

}
