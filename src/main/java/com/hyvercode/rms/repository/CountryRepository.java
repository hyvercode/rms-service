package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Country;
import com.hyvercode.rms.model.projection.country.CountryView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    @Query(value = "SELECT c.countryId AS countryId,c.countryCode AS countryCode,c.countryName AS countryName,c.active AS active FROM Country c WHERE c.active='Y'")
    Page<CountryView> findListAll(Pageable  pageable);

    @Query(value = "SELECT c.countryId AS countryId,c.countryCode AS countryCode,c.countryName AS countryName,c.active AS active FROM Country c WHERE c.countryId=:countryId AND c.active='Y'")
    Page<CountryView> findByCountryId(@Param("countryId") String countryId,Pageable  pageable);

    @Query(value = "SELECT c.countryId AS countryId,c.countryCode AS countryCode,c.countryName AS countryName,c.active AS active FROM Country c WHERE c.countryCode=:countryCode AND c.active='Y'")
    Page<CountryView> findByCountryCode(@Param("countryCode") String countryCode,Pageable  pageable);

    @Query(value = "SELECT c.countryId AS countryId,c.countryCode AS countryCode,c.countryName AS countryName,c.active AS active FROM Country c WHERE c.countryName LIKE CONCAT ('%',:countryName,'%') AND c.active='Y'")
    Page<CountryView> findByCountryName(@Param("countryName") String countryName,Pageable  pageable);

    @Query(value = "SELECT c.countryId AS countryId,c.countryCode AS countryCode,c.countryName AS countryName,c.active AS active FROM Country c WHERE c.active=:active")
    Page<CountryView> findByActive(@Param("active") String active,Pageable  pageable);

    List<Country> findByCountryNameIsContainingAndActive(String countryName, String active);
}
