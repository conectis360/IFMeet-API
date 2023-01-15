package ifsul.io.IFMeet.repository;


import ifsul.io.IFMeet.domain.Convite;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConviteRepository extends JpaRepository<Convite,Long> {
    Convite findByCodigoSecreto(String codigo);
}
