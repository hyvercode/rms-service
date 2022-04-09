package com.hyvercode.rms.model.response.district;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictResponse extends BaseResponse {
    private String districtId;
    private String districtCode;
    private String districtName;
    private String provinceCode;
    private String active;
}
