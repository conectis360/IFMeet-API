package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Documentacao;

import java.util.List;
import java.util.Optional;

public interface DocumentacaoService {

    Optional<Documentacao> findById(Long id);

    public List<Documentacao> findAll();

    public void save(Documentacao documentacao);
}
