package com.hyvercode.rms.model.response.productGroup;

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
public class GetListProductGroupResponse extends BasePaginationResponse {
    private Set<ProductGroupResponse> content;
    private Pagination pagination;
}
