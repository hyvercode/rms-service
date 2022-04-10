package com.hyvercode.rms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.model.entity.UserLogin;
import com.hyvercode.rms.model.request.userLogin.JwtRequest;
import com.hyvercode.rms.model.request.userLogin.UserRequest;
import com.hyvercode.rms.model.response.userLogin.LoginResponse;
import com.hyvercode.rms.model.response.userLogin.UserResponse;
import com.hyvercode.rms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Optional<UserLogin> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BusinessException(HttpStatus.CONFLICT, "30020", "Account Not Found");
        }
        UserLogin userLogin = user.get();
        return new org.springframework.security.core.userdetails.User(userLogin.getUsername(), userLogin.getPassword(),
                new ArrayList<>());
    }

    /**
     * @param request
     * @return
     */
    public UserResponse save(UserRequest request) {
        Optional<UserLogin> optional = userRepository.findByUsername(request.getUsername());
        if (optional.isPresent()) {
            throw new BusinessException(HttpStatus.CONFLICT, "30022", "Account already use ");
        }
        UserLogin userLogin = UserLogin.builder()
                .loginId(CommonUtil.generateUUIDString())
                .username(request.getUsername())
                .password(bcryptEncoder.encode(request.getPassword()))
                .outletId(request.getOutletId())
                .employeeId(request.getEmployeeId())
                .name(request.getName())
				.email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .avatar(request.getAvatar())
                .active(request.getActive())
                .build();
        userLogin.setCreatedBy(request.getUsername());
        userLogin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(userLogin);

        UserResponse userResponse = UserResponse.builder()
                .loginId(userLogin.getLoginId())
                .username(userLogin.getUsername())
                .build();
        return userResponse;
    }

    /**
     * @param request
     * @param token
     * @return
     */
    public LoginResponse login(JwtRequest request, String token) {
        Optional<UserLogin> optional = userRepository.findByUsername(request.getUsername());
//        if (optional.isPresent() && optional.get().getActive()) {
//            throw new BusinessException(HttpStatus.CONFLICT, "30022", "Account not active");
//        }

        UserLogin userLogin = optional.get();
        logger.info("User agent is: {}", token);
        return LoginResponse.builder()
                .accessToken("Bearer " + token)
                .employeeId(userLogin.getEmployeeId())
                .outletId(userLogin.getOutletId())
                .loginId(userLogin.getLoginId())
                .name(userLogin.getName())
                .email(userLogin.getEmail())
                .avatar(userLogin.getAvatar())
                .build();
    }

}
