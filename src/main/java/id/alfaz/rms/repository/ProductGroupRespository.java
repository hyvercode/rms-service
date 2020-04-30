package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.ProductGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGroupRespository extends JpaRepository<ProductGroup,String> {
    Page<ProductGroup> findByGroupIdAndOutletIdAndActive(String groupId, String active,String outletId, Pageable pageable);
    Page<ProductGroup> findByGroupNameIsContainingAndOutletIdAndActive(String groupId, String active,String outletId, Pageable pageable);
    Page<ProductGroup> findByOutletIdAndActive(String active,String outletId, Pageable pageable);
}
