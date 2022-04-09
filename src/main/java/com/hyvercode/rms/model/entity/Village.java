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
@Table(name = "village")
@NoArgsConstructor
@AllArgsConstructor
public class Village extends BaseEntity {
    @Id
    @Column(name = "village_id",length = 36 ,nullable = false)
    private String villageId;

    @Column(name = "postcode",length = 5,nullable = false)
    private Integer postcode;

    @Column(name = "village_name",length = 60,nullable = false)
    private String villageName;

    @Column(name = "sub_district_id",length =36,nullable = false)
    private String subDistrictId;

    @Column(name = "active",length = 1,nullable = false)
    private Boolean active;
}
