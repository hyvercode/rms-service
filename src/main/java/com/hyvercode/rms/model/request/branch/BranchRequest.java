package com.hyvercode.rms.model.request.branch;

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
public class BranchRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 36)
    private String outletId;
    @NotEmpty
    @Size(max = 60)
    private String branchName;
    @NotEmpty
    @Size(max = 15)
    private String phoneNumber;
    @Size(max = 15)
    private String phoneNumber2;
    @Size(max = 15)
    private String faxNumber;
    @NotEmpty
    @Size(max = 30)
    private String email;
    @Size(max = 30)
    private String website;
    @NotEmpty
    @Size(max = 3)
    private String countryId;
    @Size(max = 36)
    private String provinceId;
    @Size(max = 36)
    private String districtId;
    @Size(max = 36)
    private String subDistrictId;
    @Size(max = 36)
    private String villageId;
    @Size(max = 36)
    private String cityId;
    @NotEmpty
    @Size(max = 36)
    private String currencyId;

    private Integer postcode;
    @NotEmpty
    @Size(max = 255)
    private String address;
    @NotEmpty
    @Size(max = 30)
    private String contactPerson;
    @NotEmpty
    @Size(max = 30)
    private String contactNumber;
    @Size(max = 30)
    private String taxRegistrationNumber;
    @Size(max = 30)
    private String letterOfBusinessPermit;
    @Size(max = 1280)
    private String image;
    @NotEmpty
    @Size(max = 1)
    private String active;
}
