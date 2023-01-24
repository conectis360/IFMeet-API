package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Documentacao;

import java.util.List;

public interface DocumentacaoService {

    public Documentacao findById();

    public List<Documentacao> findAll();

    public void save(Documentacao documentacao);
}
