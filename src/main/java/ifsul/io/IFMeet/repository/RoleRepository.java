package ifsul.io.IFMeet.repository;


import ifsul.io.IFMeet.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoleRepository extends JpaRepository<Roles, Long> {
    List<Roles> findByRoleId(Long roleId);
}
