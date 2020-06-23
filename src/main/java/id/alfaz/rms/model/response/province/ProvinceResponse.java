package id.alfaz.rms.model.response.province;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceResponse extends BaseResponse {
    private String provinceId;
    private String provinceCode;
    private String countryCode;
    private String provinceName;
    private String active;
}
