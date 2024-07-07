package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Reuniao;

import java.util.List;
import java.util.Optional;

public interface ReuniaoService {

    Optional<Reuniao> findById(Long id);

    List<Reuniao> findAll();
    List<Reuniao> findAllByOrientador();

    void save(Reuniao reuniao);
}
