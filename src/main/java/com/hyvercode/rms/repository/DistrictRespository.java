package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRespository extends JpaRepository<District,String> {
    Page<District> findByDistrictIdAndActive(String districtId, String active, Pageable pageable);
    Page<District> findByDistrictNameIsContainingAndActive(String districtName, String active, Pageable pageable);
    Page<District> findByDistrictCodeAndActive(String districtCode, String active, Pageable pageable);
    Page<District> findByProvinceCodeAndActive(String provinceCode, String active, Pageable pageable);
    Page<District> findByActive(String active, Pageable pageable);
}
