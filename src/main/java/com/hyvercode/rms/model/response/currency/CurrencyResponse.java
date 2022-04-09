package com.hyvercode.rms.model.response.currency;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponse extends BaseResponse {
    private String currencyId;
    private String currencyName;
    private String currencySymbol;
    private Boolean active;
}
