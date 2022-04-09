package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository  extends JpaRepository<Currency,String> {

    Page<Currency> findByCurrencyIdAndActive(String currencyId, String active, Pageable pageable);
    Page<Currency> findByCurrencyNameIsContainingAndActive(String currencyName, String active, Pageable pageable);
    Page<Currency> findByCurrencySymbolAndActive(String currencySymbol, String active, Pageable pageable);
    Page<Currency> findByActive(String active, Pageable pageable);
}
