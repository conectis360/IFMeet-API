package ifsul.io.IFMeet.domain;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nomeCurso;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordenador")
    private Usuario coordenador;

}
