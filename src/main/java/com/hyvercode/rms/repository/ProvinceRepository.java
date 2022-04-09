package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,String> {
    Page<Province> findByProvinceIdAndActive(String provinceId, String active, Pageable pageable);
    Page<Province> findByProvinceCodeAndActive(String provinceCode, String active, Pageable pageable);
    Page<Province> findByProvinceNameAndActive(String provinceName, String active, Pageable pageable);
    Page<Province> findByCountryCodeAndActive(String countryCode, String active, Pageable pageable);
    Page<Province> findByActive(String active, Pageable pageable);
}
