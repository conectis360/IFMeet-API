package ifsul.io.IFMeet.domain.convite.repository;


import ifsul.io.IFMeet.domain.convite.model.Convite;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConviteRepository extends JpaRepository<Convite,Long> {
    Convite findByCodigoSecreto(String codigo);
}
