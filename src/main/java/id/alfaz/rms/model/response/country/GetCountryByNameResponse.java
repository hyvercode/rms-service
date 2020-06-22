package id.alfaz.rms.model.response.country;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCountryByNameResponse extends BaseResponse {
    private String countryId;
    private String countryCode;
    private String countryName;
}
