package com.hyvercode.rms.service;

import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Occupation;
import com.hyvercode.rms.model.request.occupation.OccupationRequest;
import com.hyvercode.rms.model.response.occupation.GetListOccupationResponse;
import com.hyvercode.rms.model.response.occupation.OccupationResponse;
import com.hyvercode.rms.repository.OccupationRepository;
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
public class OccupationService implements BaseService<PageRequest, GetListOccupationResponse> {
    private OccupationRepository occupationRepository;
    private HttpServletRequest httpServletRequest;

    public OccupationService(OccupationRepository occupationRepository, HttpServletRequest httpServletRequest) {
        this.occupationRepository = occupationRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListOccupationResponse execute(PageRequest input) {
        Page<Occupation> page = this.getPageResultByInput(input);
        Set<OccupationResponse> occupationResponses = page.getContent().stream().map(occupation -> {
            OccupationResponse response = new OccupationResponse();
            BeanUtils.copyProperties(occupation,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListOccupationResponse.builder()
                .content(occupationResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Occupation> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "occupationName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Occupation> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("occupationId")) {
            page = occupationRepository.findByOccupationIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("occupationName")){
            page = occupationRepository.findByOccupationNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = occupationRepository.findByOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = occupationRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(OccupationRequest request){
        Occupation occupation = Occupation.builder()
                .occupationId(CommonUtil.generateUUIDString())
                .occupationName(request.getOccupationName())
                .outletId(request.getOutletId())
                .remark(request.getRemark())
                .active(request.getActive())
                .build();
        occupation.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        occupation.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        occupationRepository.save(occupation);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String occupationId,OccupationRequest request){
        Optional<Occupation> optional = occupationRepository.findById(occupationId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Occupation ID Not Found");
        }

        Occupation occupation = optional.get();
        occupation.setOccupationName(request.getOccupationName());
        occupation.setOutletId(request.getOutletId());
        occupation.setRemark(request.getRemark());
        occupation.setActive(request.getActive());
        occupation.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        occupation.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        occupationRepository.save(occupation);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public OccupationResponse detail(String occupationId){
        Optional<Occupation> optional = occupationRepository.findById(occupationId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Occupation ID Not Found");
        }

        Occupation occupation = optional.get();
        OccupationResponse occupationResponse = new OccupationResponse();
        BeanUtils.copyProperties(occupation,occupationResponse);

        return occupationResponse;
    }
}
