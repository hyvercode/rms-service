package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,String> {
    Page<ProductCategory> findByCategoryIdAndOutletIdAndActive(String categoryId, String active, String outletId, Pageable pageable);
    Page<ProductCategory> findByCategoryNameIsContainingAndOutletIdAndActive(String categoryId, String active,String outletId, Pageable pageable);
    Page<ProductCategory> findByGroupIdAndOutletIdAndActive(String groupId, String active, String outletId, Pageable pageable);
    Page<ProductCategory> findByOutletIdAndActive(String active,String outletId, Pageable pageable);
}
