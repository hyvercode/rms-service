package id.alfaz.rms.controller;

import id.alfaz.rms.component.JwtTokenUtil;
import id.alfaz.rms.model.request.userLogin.JwtRequest;
import id.alfaz.rms.model.request.userLogin.UserRequest;
import id.alfaz.rms.model.response.userLogin.LoginResponse;
import id.alfaz.rms.model.response.userLogin.UserResponse;
import id.alfaz.rms.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Auth")
@RestController
@CrossOrigin
@RequestMapping("/v1/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private AuthService authService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@PostMapping (value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse createAuthenticationToken(@RequestBody JwtRequest login) throws Exception {

		authenticate(login.getUsername(), login.getPassword());

		final UserDetails userDetails = authService.loadUserByUsername(login.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		logger.info("User agent is: {}", token);
		return authService.login(login,token);
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-Outlet-ID",value = "Outlet ID",paramType = "Header")
	})
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserResponse saveUser(@RequestBody UserRequest user) throws Exception {
		return authService.save(user);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
