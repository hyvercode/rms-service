package id.alfaz.rms.model.response.productGroup;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroupResponse extends BaseResponse {
    private String groupId;
    private String groupName;
    private String outletId;
    private String remark;
    private String active;
}
