package id.alfaz.rms.model.response.bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankResponse {
    private String bankId;
    private String countryCode;
    private String bankName;
    private String bankImageLink;
    private String active;
}
