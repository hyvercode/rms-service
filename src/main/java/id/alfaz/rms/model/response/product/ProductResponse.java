package id.alfaz.rms.model.response.product;

import id.alfaz.rms.helper.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse extends BaseResponse {
    private String productId;
    private String sku;
    private String plu;
    private String barcode;
    private String productName;
    private String categoryId;
    private String brandId;
    private String typeId;
    private String uomId;
    private Integer size;
    private String tag;
    private BigDecimal retailPrice;
    private BigDecimal discountPrice;
    private BigDecimal basePrice;
    private Boolean isScale;
    private Double weight;
    private String supplierId;
    private String outletId;
    private String image;
    private Integer initialStock;
    private String remark;
    private String active;
}
