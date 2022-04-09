package com.hyvercode.rms.service;

import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Insurance;
import com.hyvercode.rms.model.request.insurance.InsuranceRequest;
import com.hyvercode.rms.model.response.insurance.GetListInsuranceResponse;
import com.hyvercode.rms.model.response.insurance.InsuranceResponse;
import com.hyvercode.rms.repository.InsuranceRepository;
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
public class InsuranceService implements BaseService<PageRequest, GetListInsuranceResponse> {
    private InsuranceRepository insuranceRepository;
    private HttpServletRequest httpServletRequest;

    public InsuranceService(InsuranceRepository insuranceRepository, HttpServletRequest httpServletRequest) {
        this.insuranceRepository = insuranceRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListInsuranceResponse execute(PageRequest input) {
        Page<Insurance> page = this.getPageResultByInput(input);
        Set<InsuranceResponse> insuranceResponses = page.getContent().stream().map(insurance -> {
            InsuranceResponse response = new InsuranceResponse();
            BeanUtils.copyProperties(insurance,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListInsuranceResponse.builder()
                .content(insuranceResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Insurance> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "insuranceName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Insurance> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("insuranceId")) {
            page = insuranceRepository.findByInsuranceIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("insuranceName")){
            page = insuranceRepository.findByInsuranceNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = insuranceRepository.findByOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = insuranceRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(InsuranceRequest request){
        Insurance insurance = Insurance.builder()
                .insuranceId(CommonUtil.generateUUIDString())
                .insuranceName(request.getInsuranceName())
                .outletId(request.getOutletId())
                .remark(request.getRemark())
                .active(request.getActive())
                .build();
        insurance.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        insurance.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        insuranceRepository.save(insurance);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String insuranceId,InsuranceRequest request){
        Optional<Insurance> optional = insuranceRepository.findById(insuranceId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Insurance ID Not Found");
        }

        Insurance insurance = optional.get();
        BeanUtils.copyProperties(request,insurance);
        insurance.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        insurance.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        insuranceRepository.save(insurance);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public InsuranceResponse detail(String insuranceId){
        Optional<Insurance> optional = insuranceRepository.findById(insuranceId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Insurance ID Not Found");
        }

        Insurance insurance = optional.get();
        InsuranceResponse response = new InsuranceResponse();
        BeanUtils.copyProperties(insurance,response);

        return response;
    }
}
