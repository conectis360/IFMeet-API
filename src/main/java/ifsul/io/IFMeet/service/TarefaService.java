package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Tarefa;

import java.util.List;

public interface TarefaService {

    public Tarefa findById();

    public List<Tarefa> findAll();

    public void save(Tarefa tarefa);
}
