package ifsul.io.IFMeet.api.usuario.dto;

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
    private Long codigoUsuario;
    private String nome;
}