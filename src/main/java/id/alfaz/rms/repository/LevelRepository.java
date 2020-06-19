package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level,String> {
    Page<Level> findByLevelIdAndOutletIdAndActive(String levelId, String active, String outletId, Pageable pageable);
    Page<Level> findByLevelNameIsContainingAndOutletIdAndActive(String levelName, String active, String outletId, Pageable pageable);
    Page<Level> findByOutletIdAndActive(String active, String outletId, Pageable pageable);
}
