package id.alfaz.rms.model.response.village;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VillageResponse {
    private String villageId;
    private Integer postcode;
    private String villageName;
    private String subDistrictId;
    private String active;
}
