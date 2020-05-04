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
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {
    @Id
    @Column(name = "category_id",length = 36,nullable = false)
    private String categoryId;
    @Column(name = "category_name",length = 60,nullable = false)
    private String categoryName;
    @Column(name = "group_id",length = 36,nullable = false)
    private String groupId;
    @Column(name = "outlet_id",length = 36,nullable = false)
    private String outletId;
    @Column(name = "remark",length = 120,nullable = false)
    private String remark;
    @Column(name = "active",length = 1,nullable = false)
    private String active;
}
