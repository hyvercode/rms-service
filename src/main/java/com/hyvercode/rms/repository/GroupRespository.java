package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRespository extends JpaRepository<Group,String> {
    Page<Group> findByGroupIdAndOutletIdAndActive(String groupId, String active, String outletId, Pageable pageable);
    Page<Group> findByGroupNameIsContainingAndOutletIdAndActive(String groupId, String active, String outletId, Pageable pageable);
    Page<Group> findByOutletIdAndActive(String active, String outletId, Pageable pageable);
}
