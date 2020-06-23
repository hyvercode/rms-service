package id.alfaz.rms.model.response.postcode;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostcodeResponse  extends BaseResponse {
    private Integer postcode;
    private String areaName;
}
