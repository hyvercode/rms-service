package com.hyvercode.rms.model.response.city;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse extends BaseResponse {
    private String cityId;
    private String cityCode;
    private String cityName;
    private String countryCode;
    private String active;
}
