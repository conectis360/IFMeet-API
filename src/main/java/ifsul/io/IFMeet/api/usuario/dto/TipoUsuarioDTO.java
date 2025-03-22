package ifsul.io.IFMeet.api.usuario.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TipoUsuarioDTO {

    private Long id;

    private String tipoUsuario;
}