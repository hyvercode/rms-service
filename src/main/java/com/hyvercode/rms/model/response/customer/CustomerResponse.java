package com.hyvercode.rms.model.response.customer;

import com.hyvercode.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse extends BaseResponse {
    private String firstName;
    private String lastName;
    private String nickName;
    private String phoneNumber;
    private String email;
    private String countryId;
    private String provinceId;
    private String districtId;
    private String subDistrictId;
    private String villageId;
    private String cityId;
    private Integer postcode;
    private String address;
    private String placeOfBirth;
    private Date birthOfDate;
    private String sex;
    private String idCardType;
    private String idCardNumber;
    private String image;
    private String outletId;
    private String active;
}
