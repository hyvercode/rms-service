package com.hyvercode.rms.service;

import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Outlet;
import com.hyvercode.rms.model.request.outlet.OutletRequest;
import com.hyvercode.rms.model.response.outlet.GetListOutletResponse;
import com.hyvercode.rms.model.response.outlet.OutletResponse;
import com.hyvercode.rms.repository.OutletRepository;
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

import static com.hyvercode.rms.helper.util.ConstantResponse.CODE_OK;
import static com.hyvercode.rms.helper.util.ConstantResponse.PROCESS_SUCCESSFULLY;

@Service
public class OutletService implements BaseService<PageRequest, GetListOutletResponse> {
    private OutletRepository outletRepository;
    private HttpServletRequest httpServletRequest;

    public OutletService(OutletRepository outletRepository, HttpServletRequest httpServletRequest) {
        this.outletRepository = outletRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListOutletResponse execute(PageRequest input) {
        Page<Outlet> page = this.getPageResultByInput(input);
        Set<OutletResponse> outletResponses = page.getContent().stream().map(outlet -> {
            OutletResponse response = new OutletResponse();
            BeanUtils.copyProperties(outlet,response);
            return response;
        }).collect(Collectors.toSet());
        return GetListOutletResponse.builder()
                .content(outletResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Outlet> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "outletName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Outlet> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("outletId")) {
            page = outletRepository.findByOutletIdAndActive(pageRequest.getSearchBy(), "Y", pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("outletName")) {
            page = outletRepository.findByOutletNameIsContainingAndActive(pageRequest.getSearchBy(),"Y", pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("countryId")) {
            page = outletRepository.findByCountryId(pageRequest.getSearchBy(),pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("currencyId")) {
            page = outletRepository.findByCurrencyId(pageRequest.getSearchBy(),pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("email")) {
            page = outletRepository.findByEmail(pageRequest.getSearchBy(),pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("phoneNumber")) {
            page = outletRepository.findByPhoneNumber(pageRequest.getSearchBy(),pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("contactPerson")) {
            page = outletRepository.findByContactPerson(pageRequest.getSearchBy(),pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")) {
            page = outletRepository.findByActive(pageRequest.getSearchBy(), pageable);
        }else{
            page = outletRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(OutletRequest request){
        Outlet outlet = Outlet.builder()
                .outletId(CommonUtil.generateUUIDString())
                .outletName(request.getOutletName())
                .phoneNumber(request.getPhoneNumber())
                .phoneNumber2(request.getPhoneNumber2())
                .faxNumber(request.getFaxNumber())
                .email(request.getEmail())
                .website(request.getWebsite())
                .countryId(request.getCountryId())
                .provinceId(request.getProvinceId())
                .districtId(request.getDistrictId())
                .subDistrictId(request.getSubDistrictId())
                .villageId(request.getVillageId())
                .cityId(request.getCityId())
                .currencyId(request.getCurrencyId())
                .postcode(request.getPostcode())
                .address(request.getAddress())
                .contactPerson(request.getContactPerson())
                .contactNumber(request.getContactNumber())
                .taxRegistrationNumber(request.getTaxRegistrationNumber())
                .letterOfBusinessPermit(request.getLetterOfBusinessPermit())
                .image(request.getImage())
                .active(request.getActive())
                .build();
        outlet.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        outlet.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        outletRepository.save(outlet);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String outletId,OutletRequest request){
        Optional<Outlet> optional = outletRepository.findById(outletId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Outlet ID Not Found");
        }

        Outlet outlet = optional.get();
        BeanUtils.copyProperties(request,outlet);
        outlet.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        outlet.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        outletRepository.save(outlet);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public OutletResponse detail(String outletId){
        Optional<Outlet> optional = outletRepository.findById(outletId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Outlet ID Not Found");
        }

        Outlet outlet = optional.get();
        OutletResponse response = new OutletResponse();
        BeanUtils.copyProperties(outlet,response);

        return response;
    }

}
