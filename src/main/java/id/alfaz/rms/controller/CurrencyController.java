package id.alfaz.rms.controller;

import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.currency.CurrencyRequest;
import id.alfaz.rms.model.response.currency.CurrencyResponse;
import id.alfaz.rms.model.response.currency.GetListCurrencyResponse;
import id.alfaz.rms.service.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags="Master Currenncy")
@RestController
@RequestMapping(value = "/v1/currency")
public class CurrencyController {
    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @ApiOperation("Get List Currency")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListCurrencyResponse listCurrency(PageRequest pageRequest){
        return currencyService.execute(pageRequest);
    }

    @ApiOperation("Post add Currency")
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody CurrencyRequest request){
       return currencyService.add(request);
    }

    @ApiOperation("Put update Currency")
    @PutMapping(value = "/update/{currencyId}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(@PathVariable("currencyId") String currencyId,@Valid @RequestBody CurrencyRequest request){
        return currencyService.update(currencyId,request);
    }

    @ApiOperation("Get detail Currency")
    @GetMapping(value = "/detail/{currencyId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CurrencyResponse detail(@PathVariable("currencyId") String currencyId){
        return currencyService.detail(currencyId);
    }
}
