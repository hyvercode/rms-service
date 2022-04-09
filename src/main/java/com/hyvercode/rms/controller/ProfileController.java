package com.hyvercode.rms.controller;

import com.hyvercode.rms.model.response.outlet.OutletResponse;
import com.hyvercode.rms.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Profile")
@RestController
@RequestMapping(value = "/v1/profile")
public class ProfileController {
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @ApiOperation("Get profile Outlet")
    @GetMapping(value = "/outlet",produces = MediaType.APPLICATION_JSON_VALUE)
    public OutletResponse outletProfile(String outletId){
        return profileService.outletProfile(outletId);
    }
}
