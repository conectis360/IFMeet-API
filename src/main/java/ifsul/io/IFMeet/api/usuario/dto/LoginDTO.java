package ifsul.io.IFMeet.api.usuario.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
