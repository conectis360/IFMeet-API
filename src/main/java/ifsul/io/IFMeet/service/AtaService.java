package ifsul.io.IFMeet.service;

import ifsul.io.IFMeet.domain.Ata;

import java.util.List;
import java.util.Optional;

public interface AtaService {

    Optional<Ata> findById(Long id);

    List<Ata> findAll();

    void save(Ata ata);
}
