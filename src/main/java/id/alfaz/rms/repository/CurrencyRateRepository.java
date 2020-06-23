package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.CurrencyRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate,String> {
    Page<CurrencyRate> findByCurrencyRateIdAndActive(String currencyId, String active, Pageable pageable);
    Page<CurrencyRate> findByDateRateAndActive(Timestamp dateRate, String active, Pageable pageable);
    Page<CurrencyRate> findByCurrencyIdIsContainingAndActive(String currencyId, String active, Pageable pageable);
    Page<CurrencyRate> findByActive(String active, Pageable pageable);
}
