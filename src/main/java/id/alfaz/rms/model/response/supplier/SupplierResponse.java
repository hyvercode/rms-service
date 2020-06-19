package id.alfaz.rms.model.response.supplier;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse extends BaseResponse {
    private String supplierId;
    private String supplierName;
    private String phoneNumber;
    private String phoneNumber2;
    private String faxNumber;
    private String email;
    private String website;
    private String countryId;
    private String provinceId;
    private String districtId;
    private String subDistrictId;
    private String villageId;
    private String cityId;
    private String currencyId;
    private Integer postcode;
    private String address;
    private String contactPerson;
    private String contactNumber;
    private String taxRegistrationNumber;
    private String letterOfBusinessPermit;
    private String outletId;
    private String image;
    private String active;
}
