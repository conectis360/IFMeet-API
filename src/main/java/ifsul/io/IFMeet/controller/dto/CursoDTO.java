package ifsul.io.IFMeet.controller.dto;

import lombok.Data;

@Data
public class CursoDTO {

    private Long codigoCurso;

    private String nomeCurso;

    private UsuarioDTO coordenador;
}
