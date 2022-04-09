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
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends BaseEntity {
    @Id
    @Column(name = "brand_id",length = 36,nullable = false)
    private String brandId;
    @Column(name = "brandName",length = 36,nullable = false)
    private String brandName;
    @Column(name = "outlet_id",length = 36,nullable = false)
    private String outletId;
    @Column(name ="remark",length = 120,nullable = true)
    private String remark;
    @Column(name = "active",length = 1,nullable = false)
    private String active;
}
