package com.hyvercode.rms.model.request.currencyRate;

import com.hyvercode.rms.helper.base.BasePaginationRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListCurrencyRateRequest extends BasePaginationRequest {
    @NotNull
    @ApiModelProperty(notes = "Date Rate")
    private Timestamp dateRate;
    @NotNull
    @ApiModelProperty(notes = "Currency ID")
    private String currencyId;
    @NotNull
    @ApiModelProperty(notes = "Currency rate")
    private BigDecimal currencyRate;
    @NotNull
    @ApiModelProperty(notes = "Active")
    private Boolean active;
}
