package com.hyvercode.rms.model.response.currency;

import com.hyvercode.rms.helper.base.BasePaginationResponse;
import com.hyvercode.rms.helper.model.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListCurrencyResponse extends BasePaginationResponse {
    private Set<CurrencyResponse> content;
    private Pagination pagination;
}
