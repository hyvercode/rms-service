package com.hyvercode.rms.model.response.uom;

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
public class GetListUomResponse extends BasePaginationResponse {
    private Set<UomResponse> content;
    private Pagination pagination;
}
