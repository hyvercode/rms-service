package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    Page<Product> findByProductIdAndOutletIdAndActive(String productId, String outletId, String active, Pageable pageable);
    Page<Product> findBySkuAndOutletIdAndActive(String sku, String outletId,String active, Pageable pageable);
    Page<Product> findByPluAndOutletIdAndActive(String plu,String outletId,String active, Pageable pageable);
    Page<Product> findByBarcodeAndOutletIdAndActive(String barcode,String outletId,String active, Pageable pageable);
    Page<Product> findByProductNameIsContainingAndOutletIdAndActive(String productName,String outletId,String active, Pageable pageable);
    Page<Product> findByCategoryIdAndOutletIdAndActive(String categoryId,String outletId,String active, Pageable pageable);
    Page<Product> findByBrandIdAndOutletIdAndActive(String branId,String outletId,String active, Pageable pageable);
    Page<Product> findByTypeIdAndOutletIdAndActive(String typeId,String outletId,String active, Pageable pageable);
    Page<Product> findByUomIdAndOutletIdAndActive(String uomId,String outletId,String active, Pageable pageable);
    Page<Product> findBySupplierIdAndOutletIdAndActive(String supplierId,String outletId,String active, Pageable pageable);
    Page<Product> findByOutletIdAndActive(String outletId,String active, Pageable pageable);
}
