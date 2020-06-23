package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.SubDistrict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubDistrictRespository extends JpaRepository<SubDistrict,String> {
    Page<SubDistrict> findBySubDistrictIdAndActive(String subDistrictId, String active, Pageable pageable);
    Page<SubDistrict> findBySubDistrictNameIsContainingAndActive(String subDistrictName, String active, Pageable pageable);
    Page<SubDistrict> findBySubDistrictCodeAndActive(String subDistrictCode, String active, Pageable pageable);
    Page<SubDistrict> findByDistrictIdAndActive(String districtId, String active, Pageable pageable);
    Page<SubDistrict> findByActive(String active, Pageable pageable);
}
