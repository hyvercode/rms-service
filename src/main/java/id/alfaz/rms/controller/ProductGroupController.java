package id.alfaz.rms.controller;

import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.productGroup.ProductGroupRequest;
import id.alfaz.rms.model.response.productGroup.GetListProductGroupResponse;
import id.alfaz.rms.model.response.productGroup.ProductGroupResponse;
import id.alfaz.rms.service.ProductGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Product Group")
@RestController
@CrossOrigin
@RequestMapping(value = "/v1/product-group")
public class ProductGroupController {
    private ProductGroupService productGroupService;

    public ProductGroupController(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    @ApiOperation("Get List Product Group")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListProductGroupResponse list(PageRequest pageRequest){
        return productGroupService.execute(pageRequest);
    }

    @ApiOperation("Post add Product group")
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody ProductGroupRequest request){
        return productGroupService.add(request);
    }

    @ApiOperation("Put update Product group")
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(String groupId,@Valid @RequestBody  ProductGroupRequest request){
        return productGroupService.update(groupId,request);
    }

    @ApiOperation("Get detail Product group")
    @GetMapping(value = "/detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupResponse detail(String groupId){
        return productGroupService.detail(groupId);
    }
}
