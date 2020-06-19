package id.alfaz.rms.model.response.occupation;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OccupationResponse extends BaseResponse {
    private String occupationId;
    private String occupationName;
    private String outletId;
    private String remark;
    private String active;
}
