package com.hyvercode.rms.controller;

import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.model.request.currencyRate.CurrencyRateRequest;
import com.hyvercode.rms.model.response.currencyRate.CurrencyRateResponse;
import com.hyvercode.rms.model.response.currencyRate.GetListCurrencyRateResponse;
import com.hyvercode.rms.service.CurrencyRateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags="Master Currenncy Rate")
@RestController
@RequestMapping(value = "/v1/currency-rate")
public class CurrencyRateController {
    private CurrencyRateService currencyRateService;

    public CurrencyRateController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @ApiOperation("Get List Currency Rate")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListCurrencyRateResponse list(PageRequest pageRequest){
        return currencyRateService.execute(pageRequest);
    }

    @ApiOperation("Post Add Currency Rate")
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody CurrencyRateRequest request){
        return currencyRateService.add(request);
    }
    @ApiOperation("Get Detail Currency Rate")
    @GetMapping(value = "/detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public CurrencyRateResponse detail(String currecnyRateId){
        return currencyRateService.detail(currecnyRateId);
    }

}
