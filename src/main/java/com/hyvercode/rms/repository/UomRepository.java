package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Uom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UomRepository extends JpaRepository<Uom,String> {
    Page<Uom> findByUomIdAndOutletIdAndActive(String uomId, String active, String outletId, Pageable pageable);
    Page<Uom> findByUomNameIsContainingAndOutletIdAndActive(String uomName, String active, String outletId, Pageable pageable);
    Page<Uom> findByOutletIdAndActive(String active, String outletId, Pageable pageable);
}
