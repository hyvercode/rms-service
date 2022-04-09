package com.hyvercode.rms.controller;

import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.model.request.country.CountryRequest;
import com.hyvercode.rms.model.response.country.CountryResponse;
import com.hyvercode.rms.model.response.country.GetListCountryByNameResponse;
import com.hyvercode.rms.model.response.country.GetListCountryRespone;
import com.hyvercode.rms.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Country")
@RestController
@RequestMapping(value = "/v1/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation("Post add Country")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Customer, ID", paramType = "header")
    })
    @PostMapping(value = "/add")
    public ApiResponse insert(@Valid @RequestBody CountryRequest request){
        return countryService.insert(request);
    }

    @ApiOperation("Put update Country")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Customer, ID", paramType = "header")
    })
    @PutMapping(value = "/update/{countryId}")
    public ApiResponse update(@PathVariable ("countryId") String countryId, @Valid @RequestBody CountryRequest request){
        return countryService.update(countryId,request);
    }

    @ApiOperation("Get list Country")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListCountryRespone getListCountries(PageRequest pageRequest){
        return countryService.execute(pageRequest);
    }

    @ApiOperation("Get detail Country")
    @GetMapping(value = "/detail/{countryId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CountryResponse detail(@PathVariable ("countryId") String countryId){
        return countryService.detail(countryId);
    }

    @ApiOperation("Get list Country By Name")
    @GetMapping(value = "/countries",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListCountryByNameResponse getCountryByName(String countryName){
        return countryService.findByName(countryName);
    }
}
