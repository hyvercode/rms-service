package id.alfaz.rms.controller;

import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.province.ProvinceRequest;
import id.alfaz.rms.model.response.province.GetListProvinceResponse;
import id.alfaz.rms.model.response.province.ProvinceResponse;
import id.alfaz.rms.service.ProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags="Master Province")
@RestController
@RequestMapping(value = "/v1/province")
public class ProvinceController {
    private ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @ApiOperation("Get List Province")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListProvinceResponse list(PageRequest pageRequest){
        return provinceService.execute(pageRequest);
    }

    @ApiOperation("Post add Province")
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody ProvinceRequest provinceRequest){
        return provinceService.add(provinceRequest);
    }

    @ApiOperation("Put update Province")
    @PutMapping(value = "/update/{provinceId}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(@PathVariable ("provinceId") String provinceId,@Valid @RequestBody ProvinceRequest provinceRequest){
        return provinceService.update(provinceId,provinceRequest);
    }

    @ApiOperation("Get detail Province")
    @GetMapping(value = "/detail/{provinceId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ProvinceResponse detail(@PathVariable ("provinceId") String provinceId){
        return provinceService.detail(provinceId);
    }
}
