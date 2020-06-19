package id.alfaz.rms.service;

import id.alfaz.rms.helper.context.ApiContext;
import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.CommonUtil;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.Supplier;
import id.alfaz.rms.model.request.supplier.SupplierRequest;
import id.alfaz.rms.model.response.supplier.GetListSupplierResponse;
import id.alfaz.rms.model.response.supplier.SupplierResponse;
import id.alfaz.rms.repository.SupplierRepository;
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
public class SupplierService implements BaseService<PageRequest, GetListSupplierResponse> {
    private SupplierRepository supplierRepository;
    private HttpServletRequest httpServletRequest;

    public SupplierService(SupplierRepository supplierRepository, HttpServletRequest httpServletRequest) {
        this.supplierRepository = supplierRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListSupplierResponse execute(PageRequest input) {
        Page<Supplier> page = this.getPageResultByInput(input);
        Set<SupplierResponse> supplierResponses = page.getContent().stream().map(supplier -> {
            SupplierResponse response = new SupplierResponse();
            BeanUtils.copyProperties(supplier,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListSupplierResponse.builder()
                .content(supplierResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Supplier> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "supplierName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Supplier> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("supplierId")) {
            page = supplierRepository.findBySupplierIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),"Y", pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("supplierName")) {
            page = supplierRepository.findBySupplierNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),"Y", pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("countryId")) {
            page = supplierRepository.findByCountryIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("currencyId")) {
            page = supplierRepository.findByCurrencyIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("email")) {
            page =supplierRepository.findByEmailAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("phoneNumber")) {
            page = supplierRepository.findByContactPersonAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("contactPerson")) {
            page = supplierRepository.findByContactPersonAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")) {
            page = supplierRepository.findByActiveAndOutletId(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = supplierRepository.findByActiveAndOutletId("Y",httpServletRequest.getHeader(ApiContext.outletId),pageable);
        }
        return page;
    }

    public ApiResponse add(SupplierRequest request){
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(request,supplier);
        supplier.setSupplierId(CommonUtil.generateUUIDString());
        supplier.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        supplier.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        supplierRepository.save(supplier);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String supplierId,SupplierRequest
            request){
        Optional<Supplier> optional = supplierRepository.findById(supplierId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Supplier ID Not Found");
        }

        Supplier supplier = optional.get();
        BeanUtils.copyProperties(request,supplier);
        supplier.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        supplier.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        supplierRepository.save(supplier);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public SupplierResponse detail(String supplierId){
        Optional<Supplier> optional = supplierRepository.findById(supplierId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Supplier ID Not Found");
        }

        Supplier supplier = optional.get();
        SupplierResponse response = new SupplierResponse();
        BeanUtils.copyProperties(supplier,response);

        return response;
    }
}
