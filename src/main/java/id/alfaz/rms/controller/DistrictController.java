package id.alfaz.rms.controller;

import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.district.DistrictRequest;
import id.alfaz.rms.model.response.district.DistrictResponse;
import id.alfaz.rms.model.response.district.GetListDistrictResponse;
import id.alfaz.rms.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master District")
@RestController
@RequestMapping(value = "/v1/district")
public class DistrictController {
    private DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @ApiOperation("Get list District")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListDistrictResponse list(PageRequest pageRequest){
        return districtService.execute(pageRequest);
    }

    @ApiOperation("Post add District")
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody DistrictRequest districtRequest){
        return districtService.add(districtRequest);
    }

    @ApiOperation("Put update District")
    @PutMapping(value = "/update/{districtId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(@PathVariable ("districtId") String districtId,@Valid @RequestBody DistrictRequest districtRequest){
        return districtService.update(districtId,districtRequest);
    }

    @ApiOperation("Get detail District")
    @GetMapping(value = "/detail/{districtId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DistrictResponse detail(@PathVariable ("districtId") String districtId){
        return districtService.detail(districtId);
    }
}
