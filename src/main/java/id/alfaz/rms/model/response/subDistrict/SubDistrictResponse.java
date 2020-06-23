package id.alfaz.rms.model.response.subDistrict;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubDistrictResponse extends BaseResponse {
    private String subDistrictId;
    private String subDistrictCode;
    private String subDistrictName;
    private String districtId;
    private String active;
}
