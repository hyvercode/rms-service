package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,String> {
    Page<Supplier> findBySupplierIdAndOutletIdAndActive(String supplierId,String outletId, String active, Pageable pageable);
    Page<Supplier> findBySupplierNameIsContainingAndOutletIdAndActive(String supplierName,String outletId, String active,Pageable pageable);
    Page<Supplier> findByCountryIdAndOutletIdAndActive(String countryId,String outletId,String active, Pageable pageable);
    Page<Supplier> findByCurrencyIdAndOutletIdAndActive(String currencyId,String outletId,String active, Pageable pageable);
    Page<Supplier> findByEmailAndOutletIdAndActive(String email,String outletId,String active, Pageable pageable);
    Page<Supplier> findByPhoneNumberAndOutletIdAndActive(String phoneNumber,String outletId,String active, Pageable pageable);
    Page<Supplier> findByContactPersonAndOutletIdAndActive(String contactPerson,String outletId,String active,Pageable pageable);
    Page<Supplier> findByActiveAndOutletId(String active,String outletId, Pageable pageable);
    Page<Supplier> findBySupplierIdAndOutletId(String supplierId,String outletId,Pageable pageable);
}
