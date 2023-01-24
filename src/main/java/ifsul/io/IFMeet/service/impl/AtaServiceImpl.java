package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Ata;
import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.repository.AtaRepository;
import ifsul.io.IFMeet.repository.CursoRepository;
import ifsul.io.IFMeet.service.AtaService;
import ifsul.io.IFMeet.service.CursoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtaServiceImpl implements AtaService {

    @Autowired
    AtaRepository ataRepository;

    @Autowired
    Messages messages;

    public Ata findById() {
        return null;
    }

    public List<Ata> findAll() {
        return null;
    }

    public void save(Ata ata) {
    }

}
