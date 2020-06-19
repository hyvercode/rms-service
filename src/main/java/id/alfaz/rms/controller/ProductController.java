package id.alfaz.rms.controller;

import id.alfaz.rms.helper.context.ApiContext;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.product.ProductRequest;
import id.alfaz.rms.model.response.product.GetListProductResponse;
import id.alfaz.rms.model.response.product.ProductResponse;
import id.alfaz.rms.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Product")
@RestController
@RequestMapping(value = "/v1/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("Get list Product")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListProductResponse list(PageRequest request){
        return productService.execute(request);
    }

    @ApiOperation("Post add Product")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody ProductRequest request){
        return productService.add(request);
    }

    @ApiOperation("Put update Product")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(String productId,@Valid @RequestBody ProductRequest request){
        return productService.update(productId,request);
    }

    @ApiOperation("Get detail Product")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @GetMapping(value = "/detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse detail(String productId){
        return productService.detail(productId);
    }
}
