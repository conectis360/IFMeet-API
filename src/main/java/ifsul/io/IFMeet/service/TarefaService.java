package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Tarefa;

import java.util.List;
import java.util.Optional;

public interface TarefaService {

    Optional<Tarefa> findById(Long id);

    public List<Tarefa> findAll();

    public void save(Tarefa tarefa);
}
