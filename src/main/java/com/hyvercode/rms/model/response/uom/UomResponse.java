package com.hyvercode.rms.model.response.uom;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UomResponse extends BaseResponse {
    private String uomId;
    private String uomName;
    private String outletId;
    private String remark;
    private Boolean active;
}
