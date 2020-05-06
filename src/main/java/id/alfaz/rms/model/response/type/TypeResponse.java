package id.alfaz.rms.model.response.type;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeResponse extends BaseResponse {
    private String typeName;
    private String outletId;
    private String remark;
    private String active;
}
