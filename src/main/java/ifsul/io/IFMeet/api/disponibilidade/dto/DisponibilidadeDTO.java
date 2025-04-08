package ifsul.io.IFMeet.api.disponibilidade.dto;

import ifsul.io.IFMeet.api.usuario.dto.UsuarioDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Data
@Getter
@Setter
public class DisponibilidadeDTO {

    private Long id;
    private UsuarioDTO usuario;
    private Integer diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

}