package com.hyvercode.rms.service;

import com.hyvercode.rms.model.request.brand.BrandRequest;
import com.hyvercode.rms.model.response.brand.BrandResponse;
import com.hyvercode.rms.model.response.brand.GetListBrandResponse;
import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Brand;
import com.hyvercode.rms.repository.BrandRepository;
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
public class BrandService implements BaseService<PageRequest, GetListBrandResponse> {

    private BrandRepository brandRepository;
    private HttpServletRequest httpServletRequest;

    public BrandService(BrandRepository brandRepository, HttpServletRequest httpServletRequest) {
        this.brandRepository = brandRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListBrandResponse execute(PageRequest input) {
        Page<Brand> page = this.getPageResultByInput(input);
        Set<BrandResponse> brandResponses = page.getContent().stream().map(brand -> {
            BrandResponse response = new BrandResponse();
            BeanUtils.copyProperties(brand,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListBrandResponse.builder()
                .content(brandResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Brand> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "brandName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Brand> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("brandId")) {
            page = brandRepository.findByBrandIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("brandName")){
            page = brandRepository.findByBrandNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = brandRepository.findByOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = brandRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(BrandRequest request){
        Brand brand = Brand.builder()
                .brandId(CommonUtil.generateUUIDString())
                .brandName(request.getBrandName())
                .remark(request.getRemark())
                .outletId(request.getOutletId())
                .active(request.getActive())
                .build();

        brand.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        brand.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        brandRepository.save(brand);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String brandId,BrandRequest request){
        Optional<Brand> optional = brandRepository.findById(brandId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Brand ID Not Found");
        }

        Brand brand = optional.get();
        brand.setBrandName(request.getBrandName());
        brand.setRemark(request.getRemark());
        brand.setActive(request.getActive());
        brand.setOutletId(request.getOutletId());
        brand.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        brand.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        brandRepository.save(brand);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public BrandResponse detail(String brandId){
        Optional<Brand> optional = brandRepository.findById(brandId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Brand ID Not Found");
        }

        Brand brand = optional.get();
        BrandResponse response = new BrandResponse();
        BeanUtils.copyProperties(brand,response);

        return response;
    }
}
