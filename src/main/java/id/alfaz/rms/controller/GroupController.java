package id.alfaz.rms.controller;

import id.alfaz.rms.helper.context.ApiContext;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.group.GroupRequest;
import id.alfaz.rms.model.response.productGroup.GetListProductGroupResponse;
import id.alfaz.rms.model.response.productGroup.ProductGroupResponse;
import id.alfaz.rms.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Group")
@RestController
@CrossOrigin
@RequestMapping(value = "/v1/group")
public class GroupController {
    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ApiOperation("Get List Group")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListProductGroupResponse list(PageRequest pageRequest){
        return groupService.execute(pageRequest);
    }

    @ApiOperation("Post add group")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody GroupRequest request){
        return groupService.add(request);
    }

    @ApiOperation("Put update group")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(String groupId,@Valid @RequestBody GroupRequest request){
        return groupService.update(groupId,request);
    }

    @ApiOperation("Get detail group")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupResponse detail(String groupId){
        return groupService.detail(groupId);
    }
}
