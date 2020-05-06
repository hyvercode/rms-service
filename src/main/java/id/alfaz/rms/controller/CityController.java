package id.alfaz.rms.controller;

import id.alfaz.rms.helper.context.ApiContext;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.city.CityRequest;
import id.alfaz.rms.model.response.city.CityResponse;
import id.alfaz.rms.model.response.city.GetListCityResponse;
import id.alfaz.rms.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master City")
@RestController
@RequestMapping(value = "/v1/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @ApiOperation("Get list City")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListCityResponse list(PageRequest request){
        return cityService.execute(request);
    }

    @ApiOperation("Post add City")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody CityRequest request){
        return cityService.add(request);
    }

    @ApiOperation("Put update City")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(String cityId,@Valid @RequestBody CityRequest request){
       return cityService.update(cityId,request);
    }

    @ApiOperation("Get detail City")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @GetMapping(value = "/detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public CityResponse detail(String cityId){
        return cityService.detail(cityId);
    }
}
