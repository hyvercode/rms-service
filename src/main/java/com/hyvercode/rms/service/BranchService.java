package com.hyvercode.rms.service;

import com.hyvercode.rms.model.response.branch.BranchResponse;
import com.hyvercode.rms.model.response.branch.GetListBranchResponse;
import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Branch;
import com.hyvercode.rms.model.request.branch.BranchRequest;
import com.hyvercode.rms.repository.BranchRepository;
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
public class BranchService implements BaseService<PageRequest, GetListBranchResponse> {
    private BranchRepository branchRepository;
    private HttpServletRequest httpServletRequest;

    public BranchService(BranchRepository branchRepository, HttpServletRequest httpServletRequest) {
        this.branchRepository = branchRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListBranchResponse execute(PageRequest input) {
        Page<Branch> page = this.getPageResultByInput(input);
        Set<BranchResponse> branchResponses = page.getContent().stream().map(branch -> {
            BranchResponse response = new BranchResponse();
            BeanUtils.copyProperties(branch,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListBranchResponse.builder()
                .content(branchResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Branch> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "branchName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Branch> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("branchId")) {
            page = branchRepository.findByBranchIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("branchName")) {
            page = branchRepository.findByBranchNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),"Y", pageable);
        } else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")) {
            page = branchRepository.findByActiveAndOutletId(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page =branchRepository.findByActiveAndOutletId("Y",httpServletRequest.getHeader(ApiContext.outletId),pageable);
        }
        return page;
    }

    public ApiResponse add(BranchRequest request){
        Branch branch = Branch.builder()
                .branchId(CommonUtil.generateUUIDString())
                .outletId(request.getOutletId())
                .branchName(request.getBranchName())
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
        branch.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        branch.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        branchRepository.save(branch);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String branchId,BranchRequest request){
        Optional<Branch> optional = branchRepository.findById(branchId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Branch ID Not Found");
        }

        Branch branch = optional.get();
        BeanUtils.copyProperties(request,branch);
        branch.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        branch.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        branchRepository.save(branch);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public BranchResponse detail(String branchId){
        Optional<Branch> optional = branchRepository.findById(branchId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Branch ID Not Found");
        }

        Branch branch = optional.get();
        BranchResponse response = new BranchResponse();
        BeanUtils.copyProperties(branch,response);

        return response;
    }

}
