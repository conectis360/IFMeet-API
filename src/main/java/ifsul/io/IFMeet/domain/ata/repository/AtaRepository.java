package ifsul.io.IFMeet.domain.ata.repository;


import ifsul.io.IFMeet.domain.ata.model.Ata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface AtaRepository extends JpaRepository<Ata, Long>, JpaSpecificationExecutor<Ata> {
}
