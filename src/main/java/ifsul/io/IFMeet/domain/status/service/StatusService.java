package ifsul.io.IFMeet.domain.status.service;

import ifsul.io.IFMeet.components.Messages;
import ifsul.io.IFMeet.domain.status.model.Status;
import ifsul.io.IFMeet.domain.status.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusService {


    private final StatusRepository statusRepository;


    private final Messages messages;

    public Optional<Status> findById(Long codigoStatus) {
        return statusRepository.findById(codigoStatus);
    }

    public List<Status> findAll() {
        return null;
    }

    public void save(Status status) {
    }

}
