package ifsul.io.IFMeet.domain.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RolesPK implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "role_id")
    private Integer roleID;

}
