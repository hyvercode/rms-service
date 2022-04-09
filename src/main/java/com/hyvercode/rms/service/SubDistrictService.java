package com.hyvercode.rms.service;

import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.SubDistrict;
import com.hyvercode.rms.model.request.subDistrict.SubDistrictRequest;
import com.hyvercode.rms.model.response.subDistrict.GetListSubDistrictResponse;
import com.hyvercode.rms.model.response.subDistrict.SubDistrictResponse;
import com.hyvercode.rms.repository.SubDistrictRespository;
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
public class SubDistrictService implements BaseService<PageRequest, GetListSubDistrictResponse> {
    private SubDistrictRespository subDistrictRespository;

    public SubDistrictService(SubDistrictRespository subDistrictRespository) {
        this.subDistrictRespository = subDistrictRespository;
    }

    @Override
    public GetListSubDistrictResponse execute(PageRequest input) {
        Page<SubDistrict> page = this.getPageResultByInput(input);
        Set<SubDistrictResponse> districtResponses=page.getContent().stream().map(district -> {
            SubDistrictResponse response = new SubDistrictResponse();
            BeanUtils.copyProperties(district,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListSubDistrictResponse.builder()
                .content(districtResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    public Page<SubDistrict> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "districtId";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<SubDistrict>  page=null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("subDistrictId")) {
            page = subDistrictRespository.findBySubDistrictIdAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("subDistrictName")){
            page = subDistrictRespository.findBySubDistrictNameIsContainingAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("subDistrictCode")){
            page = subDistrictRespository.findBySubDistrictCodeAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("districtId")){
            page = subDistrictRespository.findByDistrictIdAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = subDistrictRespository.findByActive(pageRequest.getSearchBy(), pageable);
        }else{
            page = subDistrictRespository.findByActive("Y",pageable);
        }
        return page;
    }

    public ApiResponse add(SubDistrictRequest districtRequest){
        SubDistrict district = SubDistrict.builder()
                .subDistrictId(CommonUtil.generateUUIDString())
                .subDistrictCode(districtRequest.getSubDistrictCode())
                .subDistrictName(districtRequest.getSubDistrictName())
                .districtId(districtRequest.getDistrictId())
                .active(districtRequest.getActive())
                .build();
        district.setCreatedBy("system");
        district.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        subDistrictRespository.save(district);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String districtId,SubDistrictRequest districtRequest){
        Optional<SubDistrict> optionalDistrict =subDistrictRespository.findById(districtId);
        if(!optionalDistrict.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Sub District ID Not Found");
        }
        SubDistrict district = optionalDistrict.get();
        district.setSubDistrictCode(districtRequest.getSubDistrictCode());
        district.setSubDistrictName(districtRequest.getSubDistrictName());
        district.setDistrictId(districtRequest.getDistrictId());
        district.setActive(districtRequest.getActive());
        district.setUpdatedBy("system");
        district.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        subDistrictRespository.save(district);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public SubDistrictResponse detail(String districtId){
        Optional<SubDistrict> optionalDistrict =subDistrictRespository.findById(districtId);
        if(!optionalDistrict.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Sub District ID Not Found");
        }
        SubDistrict  district= optionalDistrict.get();
        SubDistrictResponse districtResponse = new SubDistrictResponse();
        BeanUtils.copyProperties(district,districtResponse);
        return districtResponse;
    }
}
