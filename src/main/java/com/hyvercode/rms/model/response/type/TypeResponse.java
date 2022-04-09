package com.hyvercode.rms.model.response.type;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeResponse extends BaseResponse {
    private String typeName;
    private String outletId;
    private String remark;
    private Boolean active;
}
