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
@Table(name = "city")
@NoArgsConstructor
@AllArgsConstructor
public class City extends BaseEntity {
    @Id
    @Column(name = "city_id",length = 36,nullable = false)
    private String cityId;
    @Column(name = "city_code",length = 10,nullable = false)
    private String cityCode;
    @Column(name = "city_name",length = 60,nullable = false)
    private String cityName;
    @Column(name = "country_code",length = 3,nullable = false)
    private String countryCode;
    @Column(name = "active",length = 60,nullable = false)
    private Boolean active;
}
