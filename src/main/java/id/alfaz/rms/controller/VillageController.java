package id.alfaz.rms.controller;

import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.model.request.village.VillageRequest;
import id.alfaz.rms.model.response.village.GetListVillageResponse;
import id.alfaz.rms.model.response.village.VillageResponse;
import id.alfaz.rms.service.VillageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Master Village")
@RestController
@RequestMapping(value = "/v1/village")
public class VillageController {
    private VillageService villageService;

    public VillageController(VillageService villageService) {
        this.villageService = villageService;
    }

    @ApiOperation("Get list Village")
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public GetListVillageResponse list(PageRequest pageRequest){
        return villageService.execute(pageRequest);
    }

    @ApiOperation("Post add Village")
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse add(@Valid @RequestBody VillageRequest request){
        return villageService.add(request);
    }

    @ApiOperation("Put update Village")
    @PutMapping(value = "/update/{villageId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse update(@PathVariable ("villageId") String villageId,@Valid @RequestBody VillageRequest request){
        return villageService.update(villageId,request);
    }

    @ApiOperation("Get detail Sub Village")
    @GetMapping(value = "/detail/{villageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VillageResponse detail(@PathVariable ("villageId") String villageId){
        return villageService.detail(villageId);
    }
}
