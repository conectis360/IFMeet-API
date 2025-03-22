package ifsul.io.IFMeet.domain.usuario.repository;


import ifsul.io.IFMeet.config.Role;
import ifsul.io.IFMeet.domain.usuario.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long>, JpaSpecificationExecutor<TipoUsuario> {
    Optional<TipoUsuario> findByTipoUsuario(Role name);
}
