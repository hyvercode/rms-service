package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,String> {
    Page<Branch> findByBranchIdAndOutletIdAndActive(String branch,String outletId, String active, Pageable pageable);
    Page<Branch> findByBranchNameIsContainingAndOutletIdAndActive(String branchName,String outletId, String active, Pageable pageable);
    Page<Branch> findByActiveAndOutletId(String active,String outletId, Pageable pageable);

}
