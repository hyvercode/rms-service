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
import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    @Id
    @Column(name = "product_id",length = 36,nullable = false)
    private String productId;
    @Column(name = "sku",length = 20,nullable = false)
    private String sku;
    @Column(name = "plu",length = 20,nullable = false)
    private String plu;
    @Column(name = "barcode",length = 30,nullable = false)
    private String barcode;
    @Column(name = "product_name",length = 60,nullable = false)
    private String productName;
    @Column(name = "category_id",length = 36,nullable = false)
    private String categoryId;
    @Column(name = "brand_id",length = 36,nullable = false)
    private String brandId;
    @Column(name = "type_id",length = 36,nullable = false)
    private String typeId;
    @Column(name = "uom_id",length = 36,nullable = false)
    private String uomId;
    @Column(name = "size",length = 1,nullable = true)
    private Integer size;
    @Column(name = "tag",length = 3,nullable = true)
    private String tag;
    @Column(name = "retail_price",nullable = false)
    private BigDecimal retailPrice;
    @Column(name = "discount_price",nullable = true)
    private BigDecimal discountPrice;
    @Column(name = "base_price",nullable = true)
    private BigDecimal basePrice;
    @Column(name = "is_scale",nullable = true)
    private Boolean isScale;
    @Column(name = "weight",nullable = true)
    private Double weight;
    @Column(name = "supplier_id",length = 36,nullable = false)
    private String supplierId;
    @Column(name = "outlet_id",length = 36,nullable = false)
    private String outletId;
    @Column(name = "image",length = 255,nullable = true)
    private String image;
    @Column(name = "initial_stock",length = 10,nullable = true)
    private Integer initialStock;
    @Column(name = "remark",length = 255,nullable = true)
    private String remark;
    @Column(name = "active",length = 1,nullable = false)
    private String active;
}
