package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,String> {
    Page<Type> findByTypeIdAndOutletIdAndActive(String typeId, String active, String outletId, Pageable pageable);
    Page<Type> findByTypeNameIsContainingAndOutletIdAndActive(String typeName, String active, String outletId, Pageable pageable);
    Page<Type> findByOutletIdAndActive(String active, String outletId, Pageable pageable);
}
