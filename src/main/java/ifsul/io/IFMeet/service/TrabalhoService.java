package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Trabalho;

import java.util.List;

public interface TrabalhoService {

    public Trabalho findTrabalhoById(Long codigoTrabalho);
    public List<Trabalho> findAllByOrientador();
    public List<Trabalho> findAllTrabalhos();
    public void save(Trabalho trabalho);


}
