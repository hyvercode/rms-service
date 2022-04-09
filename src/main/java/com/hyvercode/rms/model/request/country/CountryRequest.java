package com.hyvercode.rms.model.request.country;

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
public class CountryRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 3)
    private String countryCode;
    @NotEmpty
    @NotEmpty
    @Size(max = 30)
    private String countryName;
    @NotEmpty
    @Size(max = 1)
    private Boolean active;
}
