package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Curso;
import ifsul.io.IFMeet.domain.Status;

import java.util.List;

public interface StatusService {

    public Status findById(Long codigoStatus);

    public List<Status> findAll();

    public void save(Status status);
}
