package com.hyvercode.rms.model.response.insurance;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceResponse extends BaseResponse {
    private String insuranceId;
    private String insuranceName;
    private String outletId;
    private String remark;
    private Boolean active;
}
