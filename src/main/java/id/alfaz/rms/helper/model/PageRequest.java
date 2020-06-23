package id.alfaz.rms.helper.model;

import id.alfaz.rms.helper.base.BasePaginationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest extends BasePaginationRequest {
    private String searchBy;
}
