package com.hyvercode.rms.model.entity;

import com.hyvercode.rms.helper.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@Table(name = "outlet")
@NoArgsConstructor
@AllArgsConstructor
public class Outlet extends BaseEntity {
    @Id
    @Column(name = "outlet_id",length = 36,nullable = false)
    private String outletId;
    @Column(name = "outlet_name",length = 60,nullable = false)
    private String outletName;
    @Column(name = "phone_number",length = 15,nullable = false)
    private String phoneNumber;
    @Column(name = "phone_number2",length = 15,nullable = true)
    private String phoneNumber2;
    @Column(name = "fax_number",length = 15,nullable = true)
    private String faxNumber;
    @Column(name = "email",length = 30,nullable = false)
    private String email;
    @Column(name = "website",length = 30,nullable = true)
    private String website;
    @Column(name = "country_id",length = 3,nullable = false)
    private String countryId;
    @Column(name = "province_id",length = 36,nullable = true)
    private String provinceId;
    @Column(name = "districtId",length = 36,nullable = true)
    private String districtId;
    @Column(name = "sub_district_id",length = 36,nullable = true)
    private String subDistrictId;
    @Column(name = "village_id",length = 36,nullable = true)
    private String villageId;
    @Column(name = "city_id",length = 36,nullable = true)
    private String cityId;
    @Column(name = "currency_id",length = 36,nullable = false)
    private String currencyId;
    @Column(name = "postcode",length = 5,nullable = true)
    private Integer postcode;
    @Column(name = "address",length = 250,nullable = false)
    private String address;
    @Column(name = "contact_person",length = 30,nullable = false)
    private String contactPerson;
    @Column(name = "contact_number",length = 15,nullable = false)
    private String contactNumber;
    @Column(name = "tax_registration_number",length = 30,nullable = true)
    private String taxRegistrationNumber;
    @Column(name = "letter_of_business_permit",length = 30,nullable = true)
    private String letterOfBusinessPermit;
    @Column(name = "image",length = 1280,nullable = true)
    private String image;
    @Column(name = "active",length = 1,nullable = false)
    private Boolean active;
}
