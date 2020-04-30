package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserLogin, Integer> {
	UserLogin findByUsernameAndOutletIdAndActive(String username,String outletId,String active);
}
