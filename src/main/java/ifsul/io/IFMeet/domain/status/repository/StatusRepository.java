package ifsul.io.IFMeet.domain.status.repository;

import ifsul.io.IFMeet.domain.status.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StatusRepository extends JpaRepository<Status, Long>, JpaSpecificationExecutor<Status> {

}
