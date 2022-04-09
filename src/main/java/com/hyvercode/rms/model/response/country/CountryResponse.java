package com.hyvercode.rms.model.response.country;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryResponse extends BaseResponse {

    private String countryId;
    private String countryCode;
    private String countryName;
    private String active;
}
