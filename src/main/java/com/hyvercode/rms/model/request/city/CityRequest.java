package com.hyvercode.rms.model.request.city;

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
public class CityRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 10)
    private String cityCode;
    @NotEmpty
    @Size(max = 60)
    private String cityName;
    @NotEmpty
    @Size(max = 3)
    private String countryCode;
    @NotEmpty
    @Size(max = 1)
    private Boolean active;
}
