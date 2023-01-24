package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Curso;

import java.util.List;

public interface CursoService {

    public Curso findById(Long codigoCurso);

    public List<Curso> findAll();

    public void save(Curso curso);
}
