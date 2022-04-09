package com.hyvercode.rms.model.response.country;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListCountryByNameResponse extends BaseResponse {
    List<GetCountryByNameResponse> content;
}
