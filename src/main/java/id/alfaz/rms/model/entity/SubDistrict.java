package id.alfaz.rms.model.entity;

import id.alfaz.rms.helper.base.BaseEntity;
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
@Table(name = "sub_district")
@NoArgsConstructor
@AllArgsConstructor
public class SubDistrict extends BaseEntity {
    @Id
    @Column(name = "sub_district_id")
    private String subDistrictId;
    @Column(name = "sub_district_code")
    private String subDistrictCode;
    @Column(name = "sub_district_name")
    private String subDistrictName;
    @Column(name = "districtId")
    private String districtId;
    @Column(name = "active")
    private String active;
}
