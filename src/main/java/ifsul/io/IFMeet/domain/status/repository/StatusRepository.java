package ifsul.io.IFMeet.domain.status.repository;

import ifsul.io.IFMeet.domain.status.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {

}
