package id.alfaz.rms.service;

import id.alfaz.rms.helper.context.ApiContext;
import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.CommonUtil;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.Uom;
import id.alfaz.rms.model.request.uom.UomRequest;
import id.alfaz.rms.model.response.uom.GetListUomResponse;
import id.alfaz.rms.model.response.uom.UomResponse;
import id.alfaz.rms.repository.UomRepository;
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

import static id.alfaz.rms.helper.util.ConstantResponse.CODE_OK;
import static id.alfaz.rms.helper.util.ConstantResponse.PROCESS_SUCCESSFULLY;

@Service
public class UomService implements BaseService<PageRequest, GetListUomResponse> {

    private UomRepository uomRepository;
    private HttpServletRequest httpServletRequest;

    public UomService(UomRepository uomRepository, HttpServletRequest httpServletRequest) {
        this.uomRepository = uomRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListUomResponse execute(PageRequest input) {
        Page<Uom> page = this.getPageResultByInput(input);
        Set<UomResponse> uomResponses = page.getContent().stream().map(uom -> {
            UomResponse response = new UomResponse();
            BeanUtils.copyProperties(uom,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListUomResponse.builder()
                .content(uomResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Uom> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "uomName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Uom> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("uomId")) {
            page = uomRepository.findByUomIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("uomName")){
            page = uomRepository.findByUomNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = uomRepository.findByOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = uomRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(UomRequest request){
        Uom uom = Uom.builder()
                .uomId(CommonUtil.generateUUIDString())
                .uomName(request.getUomName())
                .outletId(request.getOutletId())
                .remark(request.getRemark())
                .active(request.getActive())
                .build();
        uom.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        uom.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        uomRepository.save(uom);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String uomId,UomRequest request){
        Optional<Uom> optional = uomRepository.findById(uomId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","UOM ID Not Found");
        }

        Uom uom = optional.get();
        uom.setUomName(request.getUomName());
        uom.setOutletId(request.getOutletId());
        uom.setRemark(request.getRemark());
        uom.setActive(request.getActive());
        uom.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        uom.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        uomRepository.save(uom);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public UomResponse detail(String uomId){
        Optional<Uom> optional = uomRepository.findById(uomId);

        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","UOM ID Not Found");
        }

        Uom uom = optional.get();
        UomResponse uomResponse = new UomResponse();
        BeanUtils.copyProperties(uom,uomResponse);

        return uomResponse;
    }
}
