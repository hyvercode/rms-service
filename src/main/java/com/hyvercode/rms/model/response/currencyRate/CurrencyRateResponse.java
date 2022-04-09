package com.hyvercode.rms.model.response.currencyRate;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRateResponse extends BaseResponse {
    private String currencyRateId;
    private Timestamp dateRate;
    private String currencyId;
    private BigDecimal currencyRate;
    private String active;
}
