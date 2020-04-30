package id.alfaz.rms.model.response.productCategory;

import id.alfaz.rms.helper.base.BasePaginationResponse;
import id.alfaz.rms.helper.model.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListProductCategoryResponse extends BasePaginationResponse {
    private Set<ProductCategoryResponse> content;
    private Pagination pagination;
}
