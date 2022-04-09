package com.hyvercode.rms.model.response.village;

import com.hyvercode.rms.helper.base.BaseResponse;
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
public class GetListVillageResponse extends BaseResponse {
    private Set<VillageResponse> content;
    private Pagination pagination;
}
