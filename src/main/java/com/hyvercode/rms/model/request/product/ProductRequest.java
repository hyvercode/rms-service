package com.hyvercode.rms.model.request.product;

import com.hyvercode.rms.helper.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest extends BaseRequest {
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String sku;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String plu;
    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String barcode;
    @NotNull
    @NotEmpty
    @Size(max = 60)
    private String productName;
    @NotNull
    @NotEmpty
    @Size(max = 36)
    private String categoryId;
    @NotNull
    @NotEmpty
    @Size(max = 36)
    private String brandId;
    @NotNull
    @NotEmpty
    @Size(max = 36)
    private String typeId;
    @NotNull
    @NotEmpty
    @Size(max = 36)
    private String uomId;
    private Integer size;
    @Size(max = 10)
    private String tag;
    private BigDecimal retailPrice;
    private BigDecimal discountPrice;
    private BigDecimal basePrice;
    private Boolean isScale;
    private Double weight;
    @NotNull
    @NotEmpty
    @Size(max = 36)
    private String supplierId;
    @NotNull
    @NotEmpty
    @Size(max = 36)
    private String outletId;
    @Size(max = 255)
    private String image;
    private Integer initialStock;
    @Size(max = 255)
    private String remark;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private Boolean active;
}
