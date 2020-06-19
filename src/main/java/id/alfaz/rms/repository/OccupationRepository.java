package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.Occupation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation,String> {
    Page<Occupation> findByOccupationIdAndOutletIdAndActive(String occupationId, String active, String outletId, Pageable pageable);
    Page<Occupation> findByOccupationNameIsContainingAndOutletIdAndActive(String occupationName, String active, String outletId, Pageable pageable);
    Page<Occupation> findByOutletIdAndActive(String active, String outletId, Pageable pageable);
}
