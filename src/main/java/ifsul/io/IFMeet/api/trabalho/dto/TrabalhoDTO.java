package ifsul.io.IFMeet.api.trabalho.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ifsul.io.IFMeet.api.curso.dto.CursoDTO;
import ifsul.io.IFMeet.api.usuario.dto.UsuarioDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrabalhoDTO {

    private Long codigoTrabalho;

    private String titulo;

    private UsuarioDTO aluno;

    private UsuarioDTO orientador;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY' 'hh:mm:ss")
    private LocalDateTime dataInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY' 'hh:mm:ss")
    private LocalDateTime dataFim;

    private CursoDTO curso;

    private String problema;

    private String justificativa;

    private String hipotese;

    private String solucao;
}

