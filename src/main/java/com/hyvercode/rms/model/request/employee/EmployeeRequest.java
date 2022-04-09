package com.hyvercode.rms.model.request.employee;

import com.hyvercode.rms.helper.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest extends BaseRequest {
    @NotEmpty
    @Size(max = 10)
    private String employeeCode;
    @NotEmpty
    @Size(max = 30)
    private String firstName;
    @NotEmpty
    @Size(max = 30)
    private String lastName;
    @NotEmpty
    @Size(max = 30)
    private String nickName;
    @NotEmpty
    @Size(max = 15)
    private String phoneNumber;
    @NotEmpty
    @Size(max = 15)
    private String phoneNumber2;
    @NotEmpty
    @Size(max = 30)
    private String email;
    @NotEmpty
    @Size(max = 36)
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
    private Integer postcode;
    @NotEmpty
    @Size(max = 250)
    private String address;
    @NotEmpty
    @Size(max = 250)
    private String placeOfBirth;
    private Date birthOfDate;
    private Integer age;
    @NotEmpty
    @Size(max = 1)
    private String maritalStatus;
    @NotEmpty
    @Size(max = 36)
    private String occupationId;
    @NotEmpty
    @Size(max = 1)
    private String sex;
    @NotEmpty
    @Size(max = 1)
    private String idCardType;
    @NotEmpty
    @Size(max = 30)
    private String idCardNumber;
    @Size(max = 30)
    private String taxId;
    @Size(max = 36)
    private String insuranceId;
    @Size(max = 30)
    private String insuranceNumber;
    @Size(max = 1280)
    private String image;
    @NotEmpty
    @Size(max = 36)
    private String outletId;
    @NotEmpty
    @Size(max = 1)
    private String active;
}
