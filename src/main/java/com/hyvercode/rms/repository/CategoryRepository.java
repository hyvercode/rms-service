package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    Page<Category> findByCategoryIdAndOutletIdAndActive(String categoryId, String active, String outletId, Pageable pageable);
    Page<Category> findByCategoryNameIsContainingAndOutletIdAndActive(String categoryId, String active, String outletId, Pageable pageable);
    Page<Category> findByGroupIdAndOutletIdAndActive(String groupId, String active, String outletId, Pageable pageable);
    Page<Category> findByOutletIdAndActive(String active, String outletId, Pageable pageable);
}
