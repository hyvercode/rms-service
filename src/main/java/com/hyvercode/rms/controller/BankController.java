package com.hyvercode.rms.controller;

import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.model.request.bank.BankRequest;
import com.hyvercode.rms.model.response.bank.BankResponse;
import com.hyvercode.rms.model.response.bank.GetListBankResponse;
import com.hyvercode.rms.service.BankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Bank")
@RestController
@RequestMapping(value = "/v1/bank")
public class BankController {
    private BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @ApiOperation("Get List Bank")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListBankResponse listBank(PageRequest pageRequest){
            return bankService.execute(pageRequest);
    }
    @ApiOperation("Post add Bank")
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody BankRequest bankRequest){
        return bankService.add(bankRequest);
    }

    @ApiOperation("Post update Bank")
    @PostMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(@Valid @RequestBody BankRequest bankRequest){
        return bankService.update(bankRequest);
    }

    @ApiOperation("Get detail Bank")
    @GetMapping(value = "/detail/{bankId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public BankResponse detail(@PathVariable("bankId") String bankId){
        return bankService.detail(bankId);
    }
}
