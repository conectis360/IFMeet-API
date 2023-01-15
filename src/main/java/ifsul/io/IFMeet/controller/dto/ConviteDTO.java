package ifsul.io.IFMeet.controller.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ConviteDTO {
    private Long codigoConvite;

    @NotNull
    private String nomeAluno;

    @NotNull
    private String emailAluno;

    private String codigoSecreto;
}
