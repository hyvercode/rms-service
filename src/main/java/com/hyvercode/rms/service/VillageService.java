package com.hyvercode.rms.service;

import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Village;
import com.hyvercode.rms.model.request.village.VillageRequest;
import com.hyvercode.rms.model.response.village.GetListVillageResponse;
import com.hyvercode.rms.model.response.village.VillageResponse;
import com.hyvercode.rms.repository.VillageRespository;
import com.hyvercode.rms.helper.util.ConstantResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VillageService implements BaseService<PageRequest, GetListVillageResponse> {
    private VillageRespository villageRespository;

    public VillageService(VillageRespository villageRespository) {
        this.villageRespository = villageRespository;
    }

    @Override
    public GetListVillageResponse execute(PageRequest input) {
        Page<Village> page = this.getPageResultByInput(input);
        Set<VillageResponse> villageResponses = page.getContent().stream().map(village -> {
            VillageResponse response = new VillageResponse();
            BeanUtils.copyProperties(village,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListVillageResponse.builder()
                .content(villageResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    public Page<Village> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "subDistrictId";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Village>  page=null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("villageId")) {
            page = villageRespository.findByVillageIdAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("villageName")){
            page = villageRespository.findByVillageNameIsContainingAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("Postcode")){
            page = villageRespository.findByPostcodeAndActive(Integer.parseInt(pageRequest.getSearchBy()), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("subDistrictId")){
            page = villageRespository.findBySubDistrictIdAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = villageRespository.findByActive(pageRequest.getSearchBy(), pageable);
        }else{
            page = villageRespository.findByActive("Y",pageable);
        }
        return page;
    }

    public ApiResponse add(VillageRequest request){
        Village village = Village.builder()
                .villageId(CommonUtil.generateUUIDString())
                .villageName(request.getVillageName())
                .postcode(request.getPostcode())
                .subDistrictId(request.getSubDistrictId())
                .active(request.getActive())
                .build();
        village.setCreatedBy("system");
        village.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        villageRespository.save(village);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String villageId,VillageRequest request){
        Optional<Village> optional = villageRespository.findById(villageId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Village ID Not Found");
        }

        Village village = optional.get();
        village.setVillageName(request.getVillageName());
        village.setPostcode(request.getPostcode());
        village.setSubDistrictId(request.getSubDistrictId());
        village.setActive(request.getActive());
        village.setUpdatedBy("system");
        village.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        villageRespository.save(village);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public VillageResponse detail(String villageId){
        Optional<Village> optional = villageRespository.findById(villageId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Village ID Not Found");
        }

        Village village = optional.get();
        VillageResponse villageResponse = new VillageResponse();
        BeanUtils.copyProperties(village,villageResponse);

        return villageResponse;
    }
}
