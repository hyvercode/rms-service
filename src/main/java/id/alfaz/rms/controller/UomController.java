package id.alfaz.rms.controller;

import id.alfaz.rms.helper.context.ApiContext;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.uom.UomRequest;
import id.alfaz.rms.model.response.uom.GetListUomResponse;
import id.alfaz.rms.model.response.uom.UomResponse;
import id.alfaz.rms.service.UomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master UOM")
@RestController
@RequestMapping(value = "/v1/uom")
public class UomController {
    private UomService uomService;

    public UomController(UomService uomService) {
        this.uomService = uomService;
    }

    @ApiOperation("Get add UOM")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListUomResponse list(PageRequest request){
        return uomService.execute(request);
    }

    @ApiOperation("Post add UOM")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody UomRequest request){
        return uomService.add(request);
    }

    @ApiOperation("Put add Type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(String uomId,@Valid @RequestBody UomRequest request){
        return uomService.update(uomId,request);
    }

    @ApiOperation("Get add Type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public UomResponse detail(String uomId){
        return uomService.detail(uomId);
    }
}
