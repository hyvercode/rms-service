package id.alfaz.rms.model.request.insurance;

import id.alfaz.rms.helper.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 60)
    private String insuranceName;
    @NotEmpty
    @Size(max = 36)
    private String outletId;
    @NotEmpty
    @Size(max = 120)
    private String remark;
    @NotEmpty
    @Size(max = 1)
    private String active;
}
