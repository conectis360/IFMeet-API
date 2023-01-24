package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Convite;
import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.exception.exceptions.BusinessException;
import ifsul.io.IFMeet.repository.ConviteRepository;
import ifsul.io.IFMeet.repository.CursoRepository;
import ifsul.io.IFMeet.repository.UsuarioRepository;
import ifsul.io.IFMeet.security.SecurityUtils;
import ifsul.io.IFMeet.service.ConviteService;
import ifsul.io.IFMeet.service.CursoService;
import ifsul.io.IFMeet.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

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
