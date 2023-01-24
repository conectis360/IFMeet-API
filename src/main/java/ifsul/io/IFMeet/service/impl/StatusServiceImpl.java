package ifsul.io.IFMeet.service.impl;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Status;
import ifsul.io.IFMeet.repository.CursoRepository;
import ifsul.io.IFMeet.repository.StatusRepository;
import ifsul.io.IFMeet.service.CursoService;
import ifsul.io.IFMeet.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    Messages messages;

    public Status findById(Long codigoStatus) {
        return null;
    }

    public List<Status> findAll() {
        return null;
    }

    public void save(Status status) {
    }

}
