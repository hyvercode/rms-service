package id.alfaz.rms.model.request.currency;

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
public class CurrencyRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 3)
    private String currencyId;
    @NotEmpty
    @Size(max = 30)
    private String currencyName;
    @NotEmpty
    @Size(max = 5)
    private String currencySymbol;
    @NotEmpty
    @Size(max = 1)
    private String active;
}
