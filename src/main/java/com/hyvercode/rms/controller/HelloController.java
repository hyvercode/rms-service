package com.hyvercode.rms.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class HelloController {
	@ApiOperation("Get Hello")
	@ApiImplicitParams(
			@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false)
	)
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String getHello() {
		System.out.println("Im here!");
		return "Im here!";
	}
}
