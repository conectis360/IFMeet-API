package ifsul.io.IFMeet.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrabalhoDTO {

    private Long codigoTrabalho;

    private String titulo;

    private UsuarioDTO aluno;

    private UsuarioDTO orientador;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private CursoDTO curso;

    private String problema;

    private String justificativa;

    private String hipotese;

    private String solucao;
}

