package id.alfaz.rms.model.response.brand;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse extends BaseResponse {
    private String brandId;
    private String brandName;
    private String outletId;
    private String remark;
    private String active;
}
