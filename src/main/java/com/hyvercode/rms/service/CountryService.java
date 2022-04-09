package com.hyvercode.rms.service;

import com.hyvercode.rms.controller.AuthController;
import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Country;
import com.hyvercode.rms.model.projection.country.CountryView;
import com.hyvercode.rms.model.request.country.CountryRequest;
import com.hyvercode.rms.model.response.country.CountryResponse;
import com.hyvercode.rms.model.response.country.GetCountryByNameResponse;
import com.hyvercode.rms.model.response.country.GetListCountryByNameResponse;
import com.hyvercode.rms.model.response.country.GetListCountryRespone;
import com.hyvercode.rms.repository.CountryRepository;
import com.hyvercode.rms.helper.util.ConstantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static com.hyvercode.rms.helper.util.Constant.ACTIVE;

@Service
public class CountryService implements BaseService<PageRequest, GetListCountryRespone> {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private CountryRepository countryRepository;
    private HttpServletRequest httpServletRequest;

    public CountryService(CountryRepository countryRepository, HttpServletRequest httpServletRequest) {
        this.countryRepository = countryRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListCountryRespone execute(PageRequest input) {
        Page<CountryView> countryViewPage = this.getPageResultByInput(input);
        Set<CountryResponse> countryResponses = countryViewPage.getContent().stream().map(countries -> CountryResponse.builder()
                .countryId(countries.getCountryId())
                .countryCode(countries.getCountryCode())
                .countryName(countries.getCountryName())
                .active(countries.getActive())
                .build()).collect(Collectors.toCollection(LinkedHashSet::new));

        return GetListCountryRespone.builder()
                .content(countryResponses)
                .pagination(PageableUtil.pageToPagination(countryViewPage))
                .build();
    }

    private Page<CountryView> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "countryName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<CountryView> pageResult =null;
        if (pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("countryId")) {
            pageResult = countryRepository.findByCountryId(pageRequest.getSearchBy(), pageable);
        }else if (pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("countryCode")){
            pageResult = countryRepository.findByCountryCode(pageRequest.getSearchBy(),pageable);
        }else if (pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("countryName")){
            pageResult = countryRepository.findByCountryName(pageRequest.getSearchBy(),pageable);
        }else if (pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            pageResult = countryRepository.findByActive(pageRequest.getSearchBy(),pageable);
        }else {
            pageResult = countryRepository.findListAll(pageable);
        }

        return pageResult;
    }

    public ApiResponse insert(CountryRequest input) {
            Country country = Country.builder()
                    .countryId(CommonUtil.generateUUIDString())
                    .countryCode(input.getCountryCode())
                    .countryName(input.getCountryName())
                    .active(input.getActive())
                    .build();
            country.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
            country.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            countryRepository.save(country);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String countryId, CountryRequest request){
        Optional<Country>country = countryRepository.findById(countryId);
        if(!country.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Country ID Not Found");
        }

        Country countries = country.get();
        countries.setCountryCode(request.getCountryCode());
        countries.setCountryName(request.getCountryName());
        countries.setActive(request.getActive());
        countries.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        countries.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        countryRepository.save(countries);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public CountryResponse detail(String countryId){
        Optional<Country>countryOptional = countryRepository.findById(countryId);
        if(!countryOptional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Country ID Not Found");
        }
        Country country = countryOptional.get();
        return CountryResponse.builder()
                .countryId(country.getCountryId())
                .countryCode(country.getCountryCode())
                .countryName(country.getCountryName())
                .active(country.getActive())
                .build();
    }

    public GetListCountryByNameResponse findByName(String countryName){
        List<Country> countries = countryRepository.findByCountryNameIsContainingAndActive(countryName,ACTIVE);
        List<GetCountryByNameResponse> countryByNameResponses = countries.stream().map(country -> {
            GetCountryByNameResponse response = new GetCountryByNameResponse();
            BeanUtils.copyProperties(country,response);
            return response;
        }).collect(Collectors.toList());

        return GetListCountryByNameResponse.builder()
                .content(countryByNameResponses)
                .build();
    }

}
