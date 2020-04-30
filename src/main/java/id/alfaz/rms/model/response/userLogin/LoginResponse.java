package id.alfaz.rms.model.response.userLogin;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse extends BaseResponse {
	private static final long serialVersionUID = -8091879091924046844L;
	private  String accessToken;
}
