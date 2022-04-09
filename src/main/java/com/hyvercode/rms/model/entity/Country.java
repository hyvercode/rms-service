package com.hyvercode.rms.model.entity;

import com.hyvercode.rms.helper.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
public class Country extends BaseEntity {
    @Id
    @Column(name = "country_id")
    @Size(max = 36)
    private String countryId;

    @Column(name = "country_code")
    @Size(max = 3)
    private String countryCode;

    @Column(name = "country_name")
    @Size(max = 30)
    private String countryName;

    @Column(name = "active")
    @Size(max = 1)
    private Boolean active;

}
