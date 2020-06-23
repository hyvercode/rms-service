package id.alfaz.rms.model.response.village;

import id.alfaz.rms.helper.base.BaseResponse;
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
public class GetListVillageResponse extends BaseResponse {
    private Set<VillageResponse> content;
    private Pagination pagination;
}
