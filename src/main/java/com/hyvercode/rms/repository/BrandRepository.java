package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,String> {
    Page<Brand> findByBrandIdAndOutletIdAndActive(String brandId, String active, String outletId, Pageable pageable);
    Page<Brand> findByBrandNameIsContainingAndOutletIdAndActive(String brandName, String active, String outletId, Pageable pageable);
    Page<Brand> findByOutletIdAndActive(String active, String outletId, Pageable pageable);
}
