package ifsul.io.IFMeet.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Documentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reuniao_id")
    private Reuniao reuniao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabalho_id")
    private Trabalho trabalho;

    //arduino???
    @Column(name = "descricao")
    private String descricao;
}
