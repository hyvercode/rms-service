package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Bank;
import com.hyvercode.rms.model.projection.bank.BankView;
;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface BankRepository extends JpaRepository<Bank,String> {
    @Query(value = "SELECT c.bankId AS bankId,c.countryCode AS countryCode,c.bankName AS bankName,c.bankImageLink AS bankImageLink, c.active AS active FROM Bank c WHERE c.active='Y'")
    Page<BankView> findListAll(Pageable pageable);

    @Query(value = "SELECT c.bankId AS bankId,c.countryCode AS countryCode,c.bankName AS bankName,c.bankImageLink AS bankImageLink, c.active AS active FROM Bank c WHERE c.bankId =:bankId AND c.active='Y'")
    Page<BankView> findByBankId(@Param("bankId")String bankId, Pageable pageable);

    @Query(value = "SELECT c.bankId AS bankId,c.countryCode AS countryCode,c.bankName AS bankName,c.bankImageLink AS bankImageLink, c.active AS active FROM Bank c WHERE c.countryCode =:countryCode AND c.active='Y'")
    Page<BankView> findByCountryCode(@Param("countryCode")String countryCode, Pageable pageable);

    @Query(value = "SELECT c.bankId AS bankId,c.countryCode AS countryCode,c.bankName AS bankName,c.bankImageLink AS bankImageLink, c.active AS active FROM Bank c WHERE c.bankName LIKE CONCAT ('%',:bankName,'%') AND c.active='Y'")
    Page<BankView> findByBankName(@Param("bankName")String bankName, Pageable pageable);

    @Query(value = "SELECT c.bankId AS bankId,c.countryCode AS countryCode,c.bankName AS bankName,c.bankImageLink AS bankImageLink, c.active AS active FROM Bank c WHERE c.active=:active")
    Page<BankView> findByActive(@Param("active")String active, Pageable pageable);
}
