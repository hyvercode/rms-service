package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.projection.village.PostcodeView;
import com.hyvercode.rms.model.entity.Village;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VillageRespository extends JpaRepository<Village,String> {
    Page<Village> findByVillageIdAndActive(String villageId, String active, Pageable pageable);
    Page<Village> findByVillageNameIsContainingAndActive(String villageName, String active, Pageable pageable);
    Page<Village> findByPostcodeAndActive(Integer Postcode, String active, Pageable pageable);
    Page<Village> findBySubDistrictIdAndActive(String SubDistrictId, String active, Pageable pageable);
    Page<Village> findByActive(String active, Pageable pageable);

    @Query("SELECT a.postcode as postcode,a.villageName AS villageName FROM Village a WHERE a.postcode LIKE CONCAT ('%',:postcode,'%') AND a.active='Y'")
    Page<PostcodeView> getListPostCode(Integer postcode, Pageable pageable);

    @Query("SELECT a.postcode as postcode,a.villageName AS villageName FROM Village a WHERE a.villageName LIKE CONCAT ('%',:villageName,'%') AND a.active='Y'")
    Page<PostcodeView> getListPostCodeName(String villageName,Pageable pageable);

    @Query("SELECT a.postcode as postcode,a.villageName AS villageName FROM Village a WHERE a.villageName=:villageName AND a.active='Y'")
    Optional<PostcodeView> getPostCodeName(String villageName);

    @Query("SELECT a.postcode as postcode,a.villageName AS villageName FROM Village a WHERE a.postcode =:postcode AND a.active='Y'")
    Optional<PostcodeView> getPostCode(Integer postcode);
}
