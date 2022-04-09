package com.hyvercode.rms.model.request.level;

import com.hyvercode.rms.helper.base.BaseRequest;
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
public class LevelRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 60)
    private String levelName;
    @NotEmpty
    @Size(max = 60)
    private String outletId;
    @Size(max = 120)
    private String remark;
    @NotEmpty
    @Size(max = 1)
    private Boolean active;
}
