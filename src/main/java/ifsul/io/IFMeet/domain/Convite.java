package ifsul.io.IFMeet.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
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
