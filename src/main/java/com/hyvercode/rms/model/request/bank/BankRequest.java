package com.hyvercode.rms.model.request.bank;

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
public class BankRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 5)
    private String bankId;
    @NotEmpty
    @Size(max = 3)
    private String countryCode;
    @NotEmpty
    private String bankName;

    private String bankImageLink;

    @NotEmpty
    @Size(max = 1)
    private Boolean active;
}
