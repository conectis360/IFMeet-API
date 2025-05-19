package ifsul.io.IFMeet.domain.calendarevent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@Table(schema = "public", name = "view_eventos_orientador")
@DynamicUpdate
public class CalendarEventView {

    @Id
    @Column(name = "evento_id")
    private Long id;

    @Column(name = "titulo_evento")
    private String titulo;

    @Column(name = "data_inicial")
    private LocalDateTime dataInicial;

    @Column(name = "nome_aluno")
    private String nomeAluno;

    @Column(name = "trabalho")
    private String trabalhoTitulo;
}