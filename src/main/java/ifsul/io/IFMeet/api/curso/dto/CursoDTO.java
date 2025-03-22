package ifsul.io.IFMeet.api.curso.dto;

import ifsul.io.IFMeet.api.usuario.dto.UsuarioDTO;
import lombok.Data;

@Data
public class CursoDTO {

    private Long codigoCurso;

    private String nomeCurso;

    private UsuarioDTO coordenador;
}
