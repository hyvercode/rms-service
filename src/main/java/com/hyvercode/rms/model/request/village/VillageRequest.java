package com.hyvercode.rms.model.request.village;

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
public class VillageRequest extends BaseRequest {
    private Integer postcode;
    @NotEmpty
    @Size(max = 60)
    private String villageName;
    @NotEmpty
    @Size(max = 36)
    private String subDistrictId;
    @NotEmpty
    @Size(max = 1)
    private Boolean active;
}
