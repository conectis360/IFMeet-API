package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Reuniao;

import java.util.List;

public interface ReuniaoService {

    public Reuniao findById();

    public List<Reuniao> findAll();

    public void save(Reuniao reuniao);
}
