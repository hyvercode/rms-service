package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserLogin, Integer> {
	Optional <UserLogin> findByUsername(String username);
	Optional <UserLogin> findByUsernameAndPassword(String username, String password);
	UserLogin findByUsernameAndOutletIdAndActive(String username,String outletId,String active);
}
