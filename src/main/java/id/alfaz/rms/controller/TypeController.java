package id.alfaz.rms.controller;

import id.alfaz.rms.helper.context.ApiContext;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.type.TypeRequest;
import id.alfaz.rms.model.response.type.GetListTypeResponse;
import id.alfaz.rms.model.response.type.TypeResponse;
import id.alfaz.rms.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Type")
@RestController
@RequestMapping(value = "/v1/type")
public class TypeController {

    private TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @ApiOperation("Get list Type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListTypeResponse list(PageRequest request){
        return typeService.execute(request);
    }

    @ApiOperation("Post add Type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody TypeRequest request){
        return typeService.add(request);
    }


    @ApiOperation("Put update Type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(String typeId,@Valid @RequestBody TypeRequest request){
       return typeService.update(typeId,request);
    }

    @ApiOperation("Get detail Type")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public TypeResponse detail(String typeId){
        return typeService.detail(typeId);
    }
}
