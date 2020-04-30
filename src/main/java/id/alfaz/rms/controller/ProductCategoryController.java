package id.alfaz.rms.controller;

import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.productCategory.ProductCategoryRequest;
import id.alfaz.rms.model.response.productCategory.GetListProductCategoryResponse;
import id.alfaz.rms.model.response.productCategory.ProductCategoryResponse;
import id.alfaz.rms.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Product Category")
@RestController
@CrossOrigin
@RequestMapping(value = "/v1/product-category")
public class ProductCategoryController {
    private ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @ApiOperation("Get list Product Category")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListProductCategoryResponse list(PageRequest pageRequest){
        return productCategoryService.execute(pageRequest);
    }

    @ApiOperation("Post add Product Category")
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody ProductCategoryRequest request){
        return productCategoryService.add(request);
    }

    @ApiOperation("Put update Product Category")
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public  ApiResponse update(String categoryId,ProductCategoryRequest request){
        return productCategoryService.update(categoryId,request);
    }

    @ApiOperation("Get detail Product Category")
    @GetMapping(value = "/detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategoryResponse detail(String  categoryId){
        return productCategoryService.detail(categoryId);
    }
}
