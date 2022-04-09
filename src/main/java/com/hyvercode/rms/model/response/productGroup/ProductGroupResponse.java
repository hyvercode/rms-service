package com.hyvercode.rms.model.response.productGroup;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroupResponse extends BaseResponse {
    private String groupId;
    private String groupName;
    private String outletId;
    private String remark;
    private Boolean active;
}
