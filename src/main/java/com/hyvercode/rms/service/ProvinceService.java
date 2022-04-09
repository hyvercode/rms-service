package com.hyvercode.rms.service;

import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Province;
import com.hyvercode.rms.model.request.province.ProvinceRequest;
import com.hyvercode.rms.model.response.province.GetListProvinceResponse;
import com.hyvercode.rms.model.response.province.ProvinceResponse;
import com.hyvercode.rms.repository.ProvinceRepository;
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
public class ProvinceService implements BaseService<PageRequest, GetListProvinceResponse> {
    private ProvinceRepository provinceRepository;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public GetListProvinceResponse execute(PageRequest input) {
        Page<Province> page = this.getPageResultByInput(input);
        Set<ProvinceResponse> provinceResponses = page.getContent().stream().map(province -> {
            ProvinceResponse provinceResponse = new ProvinceResponse();
            BeanUtils.copyProperties(province,provinceResponse);
            return provinceResponse;
        }).collect(Collectors.toSet());

        return GetListProvinceResponse.builder()
                .content(provinceResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Province> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "countryCode";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Province> page =null;

        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("provinceId")) {
            page = provinceRepository.findByProvinceIdAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("provinceCode")){
            page = provinceRepository.findByProvinceCodeAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("provinceName")){
            page = provinceRepository.findByProvinceNameAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = provinceRepository.findByActive(pageRequest.getSearchBy(), pageable);
        }else{
            page = provinceRepository.findByActive("Y",pageable);
        }
        return page;
    }

    public ApiResponse add(ProvinceRequest provinceRequest){
        Province province = Province.builder()
                .provinceId(CommonUtil.generateUUIDString())
                .provinceCode(provinceRequest.getProvinceCode())
                .countryCode(provinceRequest.getCountryCode())
                .provinceName(provinceRequest.getProvinceName())
                .active(provinceRequest.getActive())
                .build();
        province.setCreatedBy("system");
        province.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        provinceRepository.save(province);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String provinceId,ProvinceRequest provinceRequest){
        Optional<Province> optional = provinceRepository.findById(provinceId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Province ID Not Found");
        }

        Province province = optional.get();
        province.setProvinceCode(provinceRequest.getProvinceCode());
        province.setProvinceName(provinceRequest.getProvinceName());
        province.setCountryCode(provinceRequest.getCountryCode());
        province.setActive(provinceRequest.getActive());
        province.setUpdatedBy("system");
        province.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        provinceRepository.save(province);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public ProvinceResponse detail(String provinceId){
        Optional<Province> optional = provinceRepository.findById(provinceId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Province ID Not Found");
        }

        Province province = optional.get();

        return ProvinceResponse.builder()
                .provinceId(province.getProvinceId())
                .provinceCode(province.getProvinceCode())
                .provinceName(province.getProvinceName())
                .countryCode(province.getCountryCode())
                .active(province.getActive())
                .build();
    }
}
