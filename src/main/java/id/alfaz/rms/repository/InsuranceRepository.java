package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.Insurance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance,String> {
    Page<Insurance> findByInsuranceIdAndOutletIdAndActive(String insuranceId, String active, String outletId, Pageable pageable);
    Page<Insurance> findByInsuranceNameIsContainingAndOutletIdAndActive(String insuranceName, String active, String outletId, Pageable pageable);
    Page<Insurance> findByOutletIdAndActive(String active, String outletId, Pageable pageable);
}
