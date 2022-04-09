package com.hyvercode.rms.service;

import com.hyvercode.rms.model.request.type.TypeRequest;
import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Type;
import com.hyvercode.rms.model.response.type.GetListTypeResponse;
import com.hyvercode.rms.model.response.type.TypeResponse;
import com.hyvercode.rms.repository.TypeRepository;
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
public class TypeService implements BaseService<PageRequest, GetListTypeResponse> {

    private TypeRepository typeRepository;
    private HttpServletRequest httpServletRequest;

    public TypeService(TypeRepository typeRepository, HttpServletRequest httpServletRequest) {
        this.typeRepository = typeRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListTypeResponse execute(PageRequest input) {
        Page<Type> page = this.getPageResultByInput(input);
        Set<TypeResponse> typeResponses = page.getContent().stream().map(type -> {
            TypeResponse response = new TypeResponse();
            BeanUtils.copyProperties(type,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListTypeResponse.builder()
                .content(typeResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Type> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "typeName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Type> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("typeId")) {
            page = typeRepository.findByTypeIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("typeName")){
            page = typeRepository.findByTypeNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = typeRepository.findByOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = typeRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(TypeRequest request){
        Type type = Type.builder()
                .typeId(CommonUtil.generateUUIDString())
                .typeName(request.getTypeName())
                .outletId(request.getOutletId())
                .remark(request.getRemark())
                .active(request.getActive())
                .build();
        type.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        type.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        typeRepository.save(type);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String typeId,TypeRequest request){
        Optional<Type> optional = typeRepository.findById(typeId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Type ID Not Found");
        }

        Type type = optional.get();
        type.setTypeName(request.getTypeName());
        type.setOutletId(request.getOutletId());
        type.setRemark(request.getRemark());
        type.setActive(request.getActive());
        type.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        type.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        typeRepository.save(type);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public TypeResponse detail(String typeId){
        Optional<Type> optional = typeRepository.findById(typeId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Type ID Not Found");
        }

        Type type = optional.get();
        TypeResponse response = new TypeResponse();
        BeanUtils.copyProperties(type,response);

        return response;
    }
}
