package ifsul.io.IFMeet.domain.tcc.model;

import ifsul.io.IFMeet.domain.trabalho.model.Trabalho;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "public", name = "tcc")
public class TCC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Trabalho trabalho;

    @Column(name = "versao")
    private String versao;

    @Column(name = "comentario")
    private String comentario;
}
