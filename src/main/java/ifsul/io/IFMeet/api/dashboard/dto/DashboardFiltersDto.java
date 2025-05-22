package ifsul.io.IFMeet.api.dashboard.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DashboardFiltersDto {

    private Long codigoTrabalho;
    private Long codigoReuniao;

}
