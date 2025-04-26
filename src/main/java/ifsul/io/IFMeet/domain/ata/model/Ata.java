package ifsul.io.IFMeet.domain.ata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ifsul.io.IFMeet.domain.calendarevent.model.CalendarEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(schema = "public", name = "ata")
public class Ata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private CalendarEvent evento;

    @Column(name = "resumo")
    private String resumo;

    @Column(name = "pauta")
    private String pauta;

}
