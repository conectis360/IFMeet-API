package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Tarefa;
import ifsul.io.IFMeet.repository.TarefaRepository;
import ifsul.io.IFMeet.service.TarefaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    Messages messages;

    public Optional<Tarefa> findById(Long id) {
        return tarefaRepository.findById(id);
    }

    public List<Tarefa> findAll() {
        return null;
    }

    public void save(Tarefa tarefa) {
    }

}
