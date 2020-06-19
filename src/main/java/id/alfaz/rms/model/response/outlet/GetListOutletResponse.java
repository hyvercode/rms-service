package id.alfaz.rms.model.response.outlet;

import id.alfaz.rms.helper.base.BasePaginationResponse;
import id.alfaz.rms.helper.model.Pagination;
import id.alfaz.rms.model.entity.Outlet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListOutletResponse extends BasePaginationResponse {
    private Set<OutletResponse> content;
    private Pagination pagination;
}
