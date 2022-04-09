package com.hyvercode.rms.model.request.subDistrict;

import com.hyvercode.rms.helper.base.BaseRequest;
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
public class SubDistrictRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 10)
    private String subDistrictCode;
    @NotEmpty
    @Size(max = 60)
    private String subDistrictName;
    @NotEmpty
    @Size(max = 36)
    private String districtId;
    @NotEmpty
    @Size(max = 1)
    private String active;
}
