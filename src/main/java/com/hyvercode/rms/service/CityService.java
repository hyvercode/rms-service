package com.hyvercode.rms.service;

import com.hyvercode.rms.model.request.city.CityRequest;
import com.hyvercode.rms.model.response.city.CityResponse;
import com.hyvercode.rms.model.response.city.GetListCityResponse;
import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.ConstantResponse;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.City;
import com.hyvercode.rms.repository.CityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityService implements BaseService<PageRequest, GetListCityResponse> {

    private CityRepository cityRepository;
    private HttpServletRequest httpServletRequest;

    public CityService(CityRepository cityRepository, HttpServletRequest httpServletRequest) {
        this.cityRepository = cityRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListCityResponse execute(PageRequest input) {
        Page<City> page = this.getPageResultByInput(input);
        Set<CityResponse>  cityResponses = page.getContent().stream().map(city -> {
            CityResponse response = new CityResponse();
            BeanUtils.copyProperties(city,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListCityResponse.builder()
                .content(cityResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<City> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "countryCode";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<City> page =null;

        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("cityId")) {
            page = cityRepository.findByCityIdAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("cityCode")){
            page = cityRepository.findByCityCodeAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("cityName")){
            page = cityRepository.findByCityNameAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = cityRepository.findByActive(pageRequest.getSearchBy(), pageable);
        }else{
            page = cityRepository.findByActive("Y",pageable);
        }
        return page;
    }

    public ApiResponse add(CityRequest request){
        City city = City.builder()
                .cityId(CommonUtil.generateUUIDString())
                .cityName(request.getCityName())
                .cityCode(request.getCityCode())
                .countryCode(request.getCountryCode())
                .active(request.getActive())
                .build();
        city.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        city.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        cityRepository.save(city);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String cityId,CityRequest request){
        Optional<City> optional = cityRepository.findById(cityId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","City ID Not Found");
        }

        City city = optional.get();
        city.setCityName(request.getCityName());
        city.setCityCode(request.getCityCode());
        city.setActive(request.getActive());
        city.setCountryCode(request.getCountryCode());
        city.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        city.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        cityRepository.save(city);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public CityResponse detail(String cityId){
        Optional<City> optional = cityRepository.findById(cityId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","City ID Not Found");
        }

        City city = optional.get();
        CityResponse cityResponse = new CityResponse();
        BeanUtils.copyProperties(city,cityResponse);

        return cityResponse;
    }
}
