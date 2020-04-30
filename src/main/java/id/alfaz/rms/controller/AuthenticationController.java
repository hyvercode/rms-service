package id.alfaz.rms.controller;

import id.alfaz.rms.component.JwtTokenUtil;
import id.alfaz.rms.model.request.userLogin.JwtRequest;
import id.alfaz.rms.model.request.userLogin.UserRequest;
import id.alfaz.rms.model.response.userLogin.LoginResponse;
import id.alfaz.rms.model.response.userLogin.UserResponse;
import id.alfaz.rms.service.UserLoginService;
import io.swagger.annotations.Api;
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
public class AuthenticationController {

	private AuthenticationManager authenticationManager;

	private JwtTokenUtil jwtTokenUtil;

	private UserLoginService userDetailsService;

	public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserLoginService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping (value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return LoginResponse.builder().accessToken("Bearer "+token).build();
	}

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserResponse saveUser(@RequestBody UserRequest user) throws Exception {
		return userDetailsService.save(user);
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
