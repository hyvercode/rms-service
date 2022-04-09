package com.hyvercode.rms.controller;

import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.model.request.subDistrict.SubDistrictRequest;
import com.hyvercode.rms.model.response.subDistrict.GetListSubDistrictResponse;
import com.hyvercode.rms.model.response.subDistrict.SubDistrictResponse;
import com.hyvercode.rms.service.SubDistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Sub District")
@RestController
@RequestMapping(value = "/v1/sub-district")
public class SubDistrictController {
    private SubDistrictService subDistrictService;

    public SubDistrictController(SubDistrictService subDistrictService) {
        this.subDistrictService = subDistrictService;
    }

    @ApiOperation("Get list Sub District")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListSubDistrictResponse list(PageRequest pageRequest){
        return subDistrictService.execute(pageRequest);
    }

    @ApiOperation("Post add Sub District")
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody SubDistrictRequest districtRequest){
        return subDistrictService.add(districtRequest);
    }

    @ApiOperation("Put update Sub District")
    @PutMapping(value = "/update/{subDistrictId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(@PathVariable ("subDistrictId") String subDistrictId,@Valid @RequestBody SubDistrictRequest districtRequest){
        return subDistrictService.update(subDistrictId,districtRequest);
    }

    @ApiOperation("Get detail Sub District")
    @GetMapping(value = "/detail/{subDistrictId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SubDistrictResponse detail(@PathVariable ("subDistrictId") String subDistrictId){
        return subDistrictService.detail(subDistrictId);
    }
}
