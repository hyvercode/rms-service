package id.alfaz.rms.model.response.customer;

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
public class GetListCustomerResponse extends BasePaginationResponse {
    private Set<CustomerResponse> content;
    private Pagination pagination;
}
