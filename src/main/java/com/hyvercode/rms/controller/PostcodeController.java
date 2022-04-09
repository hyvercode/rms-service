package com.hyvercode.rms.controller;

import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.model.response.postcode.GetListPostcodeResponse;
import com.hyvercode.rms.model.response.postcode.PostcodeResponse;
import com.hyvercode.rms.service.PostcodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Master Postcode")
@RestController
@RequestMapping(value = "/v1/postcode")
public class PostcodeController {
    private PostcodeService postcodeService;

    public PostcodeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @ApiOperation("Get List Postcode")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListPostcodeResponse list(PageRequest pageRequest){
      return   postcodeService.execute(pageRequest);
    }

    @ApiOperation(("Get Postcode by Code"))
    @GetMapping(value = "/code")
    public PostcodeResponse findByPostcode(Integer postcode){
        return postcodeService.findByPostcode(postcode);
    }

    @ApiOperation(("Get Postcode by Area"))
    @GetMapping(value = "/area")
    public PostcodeResponse findByPostcodeResponse(String areaName){
        return postcodeService.findByPostcodeName(areaName);
    }
}
