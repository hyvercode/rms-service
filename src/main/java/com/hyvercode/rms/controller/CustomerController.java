package com.hyvercode.rms.controller;

import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.model.request.customer.CustomerRequest;
import com.hyvercode.rms.model.response.customer.CustomerResponse;
import com.hyvercode.rms.model.response.customer.GetListCustomerResponse;
import com.hyvercode.rms.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Customer")
@RestController
@RequestMapping(value = "/v1/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation("Get list Customer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListCustomerResponse list(PageRequest request) {
        return customerService.execute(request);
    }

    @ApiOperation("Post add Customer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Customer, ID", paramType = "header")
    })
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody CustomerRequest request) {
        return customerService.add(request);
    }

    @ApiOperation("Put update Customer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header"),
            @ApiImplicitParam(name = ApiContext.employeeId, value = "Customer, ID", paramType = "header")
    })
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(String customerId, @Valid @RequestBody CustomerRequest request) {
        return customerService.update(customerId, request);
    }

    @ApiOperation("Get detail Customer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ApiContext.outletId, value = "Outlet ID", paramType = "header")
    })
    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse detail(String customerId) {
        return customerService.detail(customerId);
    }
}