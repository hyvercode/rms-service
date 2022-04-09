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
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@Table(name = "province")
@NoArgsConstructor
@AllArgsConstructor
public class Province extends BaseEntity {
    @Id
    @Column(name = "province_id")
    @Size(max = 36)
    private String provinceId;

    @Column(name = "province_code")
    @Size(max = 10)
    private String provinceCode;

    @Column(name = "countryCode")
    @Size(max = 3)
    private String countryCode;

    @Column(name = "province_name")
    @Size(max = 60)
    private String provinceName;

    @Column(name = "active")
    @Size(max = 1)
    private Boolean active;
}
