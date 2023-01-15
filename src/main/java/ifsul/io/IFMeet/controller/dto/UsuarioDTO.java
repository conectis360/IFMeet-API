package ifsul.io.IFMeet.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String nome;
}