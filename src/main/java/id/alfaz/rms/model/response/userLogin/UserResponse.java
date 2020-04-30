package id.alfaz.rms.model.response.userLogin;

import id.alfaz.rms.helper.base.BaseResponse;
import id.alfaz.rms.helper.model.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse extends BaseResponse {
    private String userId;
    private String username;
}
