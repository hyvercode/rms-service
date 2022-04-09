package com.hyvercode.rms.model.response.level;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelResponse extends BaseResponse {
    private String levelId;
    private String levelName;
    private String outletId;
    private String remark;
    private String active;
}
