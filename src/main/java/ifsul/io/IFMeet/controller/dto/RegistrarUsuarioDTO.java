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
public class RegistrarUsuarioDTO {
        private Long id;

        @NotBlank
        @Size(min = 3, max = 20)
        private String usuario;

        @NotBlank
        @Size(max = 50)
        @Email
        private String email;

        private Set<String> role;

        @NotBlank
        @Size(min = 6, max = 10)
        private String senha;
}
