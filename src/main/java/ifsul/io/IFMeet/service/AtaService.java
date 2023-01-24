package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Ata;

import java.util.List;

public interface AtaService {

    Ata findById();

    List<Ata> findAll();

    void save(Ata ata);
}
