package id.alfaz.rms.model.request.userLogin;

import id.alfaz.rms.helper.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 30)
    private String username;
    @NotEmpty
    @Size(min = 8,max = 30)
    private String password;
    @NotEmpty
    @Size(max = 36)
    private String outletId;
    @NotEmpty
    @Size(max = 36)
    private String employeeId;
    @NotEmpty
    @Size(max = 1)
    private String active;
}
