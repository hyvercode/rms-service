package id.alfaz.rms.model.request.customer;

import id.alfaz.rms.helper.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest extends BaseRequest {
    private String customerId;
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
