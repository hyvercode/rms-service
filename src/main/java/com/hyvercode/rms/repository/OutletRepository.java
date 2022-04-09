package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Outlet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutletRepository extends JpaRepository<Outlet,String> {
    Page<Outlet> findByOutletIdAndActive(String outletId, String active,Pageable pageable);
    Page<Outlet> findByOutletNameIsContainingAndActive(String outletName, String active,Pageable pageable);
    Page<Outlet> findByCountryId(String countryId, Pageable pageable);
    Page<Outlet> findByCurrencyId(String currencyId, Pageable pageable);
    Page<Outlet> findByEmail(String email, Pageable pageable);
    Page<Outlet> findByPhoneNumber(String phoneNumber, Pageable pageable);
    Page<Outlet> findByContactPerson(String contactPerson,Pageable pageable);
    Page<Outlet> findByActive(String active, Pageable pageable);
    Page<Outlet> findByOutletId(String outletId,Pageable pageable);
}
