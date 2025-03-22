package ifsul.io.IFMeet.domain.curso.service;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.curso.model.Curso;
import ifsul.io.IFMeet.domain.curso.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    Messages messages;

    public Curso findById(Long codigoCurso) {
        Optional<Curso> cursoOpt = cursoRepository.findById(codigoCurso);
        Curso curso = cursoOpt.orElse(null);
        return curso;
    }

    public List<Curso> findAll() {
        List<Curso> listaCursos = new ArrayList<>();
        listaCursos = cursoRepository.findAll();
        return listaCursos;
    }

    public void save(Curso curso) {
        cursoRepository.save(curso);
    }

}
