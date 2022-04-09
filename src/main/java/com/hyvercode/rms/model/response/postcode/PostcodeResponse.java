package com.hyvercode.rms.model.response.postcode;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostcodeResponse  extends BaseResponse {
    private Integer postcode;
    private String areaName;
}
