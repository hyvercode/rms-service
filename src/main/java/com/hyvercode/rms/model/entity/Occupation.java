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
@Table(name = "occupation")
@NoArgsConstructor
@AllArgsConstructor
public class Occupation extends BaseEntity {
    @Id
    @Column(name = "occupation_id",length = 36,nullable = false)
    private String occupationId;
    @Column(name = "occupation_name",length = 36,nullable = false)
    private String occupationName;
    @Column(name = "outlet_id",length = 36,nullable = false)
    private String outletId;
    @Column(name = "remark",length = 120,nullable = true)
    private String remark;
    @Column(name = "active",length = 1,nullable = false)
    private Boolean active;
}
