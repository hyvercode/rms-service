package id.alfaz.rms.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.util.CommonUtil;
import id.alfaz.rms.model.entity.UserLogin;
import id.alfaz.rms.model.request.userLogin.UserRequest;
import id.alfaz.rms.model.response.userLogin.UserResponse;
import id.alfaz.rms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserLoginService implements UserDetailsService {

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserLogin userLogin = userRepository.findByUsernameAndOutletIdAndActive(username,"root","Y");
		if (userLogin == null) {
			throw new BusinessException(HttpStatus.CONFLICT,"30020","Username Not Found");
		}
		return new org.springframework.security.core.userdetails.User(userLogin.getUsername(), userLogin.getPassword(),
					new ArrayList<>());
	}

	public UserResponse save(UserRequest request) {
		UserLogin  userLogin = UserLogin.builder()
				.loginId(CommonUtil.generateUUIDString())
				.username(request.getUsername())
				.password(bcryptEncoder.encode(request.getPassword()))
				.outletId(request.getOutletId())
				.employeeId(request.getEmployeeId())
				.active(request.getActive())
				.build();
		userLogin.setCreatedBy(request.getUsername());
		userLogin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		userRepository.save(userLogin);

		UserResponse userResponse = UserResponse.builder()
				.userId(userLogin.getLoginId())
				.username(userLogin.getUsername())
				.build();
		return userResponse;
	}

}
