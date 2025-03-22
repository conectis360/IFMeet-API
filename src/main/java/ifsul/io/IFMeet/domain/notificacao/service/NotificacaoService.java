package ifsul.io.IFMeet.domain.notificacao.service;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.notificacao.model.Notificacao;
import ifsul.io.IFMeet.domain.notificacao.repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificacaoService {


    private final NotificacaoRepository notificacaoRepository;


    private final Messages messages;

    public Optional<Notificacao> findById(Long codigoStatus) {
        return notificacaoRepository.findById(codigoStatus);
    }

    public List<Notificacao> findAll() {
        return null;
    }

    public void save(Notificacao status) {
    }

}
