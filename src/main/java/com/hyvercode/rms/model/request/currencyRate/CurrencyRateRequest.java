package com.hyvercode.rms.model.request.currencyRate;

import com.hyvercode.rms.helper.base.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRateRequest extends BaseRequest {

    @ApiModelProperty(notes = "Date Rate")
    private String dateRate;
    @NotEmpty
    @Size(max = 3)
    @ApiModelProperty(notes = "Currency ID")
    private String currencyId;
    @ApiModelProperty(notes = "Currency Rate")
    private BigDecimal currencyRate;
    @NotEmpty
    @Size(max = 1)
    @ApiModelProperty(notes = "Active")
    private String active;
}
