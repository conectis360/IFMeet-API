package ifsul.io.IFMeet.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@Table(schema = "public", name = "user_roles")
public class Roles implements Serializable {

    @EmbeddedId
    private RolesPK id;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private Usuario usuario;

    @Column(name = "role_id", updatable = false, insertable = false)
    private Long roleId;
}
