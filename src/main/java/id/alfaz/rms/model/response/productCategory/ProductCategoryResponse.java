package id.alfaz.rms.model.response.productCategory;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryResponse extends BaseResponse {
    private String categoryId;
    private String categoryName;
    private String groupId;
    private String outletId;
    private String remark;
    private String active;
}
