package ifsul.io.IFMeet.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegistrarDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String usuario;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> permissoes;

    @NotBlank
    @Size(min = 6, max = 40)
    private String senha;
}
