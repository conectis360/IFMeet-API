package ifsul.io.IFMeet.api.ata.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AtaFilterDto {

    private Long codigoTrabalho;
    private Long codigoReuniao;

}
