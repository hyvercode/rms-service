package id.alfaz.rms.model.request.district;

import id.alfaz.rms.helper.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 10)
    private String districtCode;
    @NotEmpty
    @Size(max = 60)
    private String districtName;
    @NotEmpty
    @Size(max = 10)
    private String provinceCode;
    @NotEmpty
    @Size(max = 1)
    private String active;
}
