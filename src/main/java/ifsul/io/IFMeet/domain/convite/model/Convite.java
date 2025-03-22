package ifsul.io.IFMeet.domain.convite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ifsul.io.IFMeet.domain.usuario.model.Usuario;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(schema = "public", name = "convite")
public class Convite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id")
    private Long codigoConvite;

    @Column(name = "nomeAluno")
    private String nomeAluno;

    @Column(name = "emailAluno")
    private String emailAluno;

    @Column(name = "codigoSecreto")
    private String codigoSecreto;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioResponsavel;

}
