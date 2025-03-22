package ifsul.io.IFMeet.api.status.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StatusDTO {

    private Long codigoStatus;
    private String status;
}