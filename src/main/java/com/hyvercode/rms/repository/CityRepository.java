package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,String> {
    Page<City> findByCityIdAndActive(String cityId, String active, Pageable pageable);
    Page<City> findByCityCodeAndActive(String cityCode, String active, Pageable pageable);
    Page<City> findByCityNameAndActive(String cityName, String active, Pageable pageable);
    Page<City> findByCountryCodeAndActive(String countryCode, String active, Pageable pageable);
    Page<City> findByActive(String active, Pageable pageable);
}
