package com.hyvercode.rms.controller;

import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.model.request.category.CategoryRequest;
import com.hyvercode.rms.model.response.productCategory.GetListProductCategoryResponse;
import com.hyvercode.rms.model.response.productCategory.ProductCategoryResponse;
import com.hyvercode.rms.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Category")
@RestController
@CrossOrigin
@RequestMapping(value = "/v1/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation("Get list Category")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListProductCategoryResponse list(PageRequest pageRequest){
        return categoryService.execute(pageRequest);
    }

    @ApiOperation("Post add Category")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody CategoryRequest request){
        return categoryService.add(request);
    }

    @ApiOperation("Put update Product Category")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Employee, ID", paramType = "header")
    })
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public  ApiResponse update(String categoryId,@Valid @RequestBody CategoryRequest request){
        return categoryService.update(categoryId,request);
    }

    @ApiOperation("Get detail Category")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategoryResponse detail(String  categoryId){
        return categoryService.detail(categoryId);
    }
}
