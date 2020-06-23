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
@Table(name = "district")
@NoArgsConstructor
@AllArgsConstructor
public class District extends BaseEntity {
    @Id
    @Column(name = "district_id")
    private String districtId;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "district_name")
    private String districtName;
    @Column(name = "province_code")
    private String provinceCode;
    @Column(name = "active")
    private String active;
}
