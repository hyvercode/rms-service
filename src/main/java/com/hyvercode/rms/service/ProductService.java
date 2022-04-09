package com.hyvercode.rms.service;

import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Product;
import com.hyvercode.rms.model.request.product.ProductRequest;
import com.hyvercode.rms.model.response.product.GetListProductResponse;
import com.hyvercode.rms.model.response.product.ProductResponse;
import com.hyvercode.rms.repository.ProductRepository;
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

import static com.hyvercode.rms.helper.util.Constant.ACTIVE;
import static com.hyvercode.rms.helper.util.ConstantResponse.CODE_OK;
import static com.hyvercode.rms.helper.util.ConstantResponse.PROCESS_SUCCESSFULLY;

@Service
public class ProductService implements BaseService<PageRequest, GetListProductResponse> {
    private ProductRepository productRepository;
    private HttpServletRequest httpServletRequest;

    public ProductService(ProductRepository productRepository, HttpServletRequest httpServletRequest) {
        this.productRepository = productRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListProductResponse execute(PageRequest input) {
        Page<Product> page = this.getPageResultByInput(input);
        Set<ProductResponse> productResponses = page.getContent().stream().map(product -> {
            ProductResponse response = new ProductResponse();
            BeanUtils.copyProperties(product,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListProductResponse.builder()
                .content(productResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Product> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "productName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Product> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("productId")) {
            page = productRepository.findByProductIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("sku")) {
            page = productRepository.findBySkuAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("plu")) {
            page = productRepository.findByPluAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("barcode")) {
            page = productRepository.findByBarcodeAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("productName")) {
            page = productRepository.findByProductNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("categoryId")) {
            page = productRepository.findByCategoryIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("brandId")) {
            page = productRepository.findByBrandIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("typeId")) {
            page = productRepository.findByTypeIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("uomId")) {
            page = productRepository.findByUomIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("supplierId")) {
            page = productRepository.findBySupplierIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")) {
            page = productRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),pageRequest.getSearchBy(),pageable);
        }else{
            page = productRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),ACTIVE,pageable);
        }
        return page;
    }

    public ApiResponse add(ProductRequest request){
        Product product = new Product();
        BeanUtils.copyProperties(request,product);
        product.setProductId(CommonUtil.generateUUIDString());
        product.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        productRepository.save(product);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String productId,ProductRequest request){
        Optional<Product> optional = productRepository.findById(productId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Product ID Not Found");
        }

        Product product = optional.get();
        BeanUtils.copyProperties(request,product);
        product.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        productRepository.save(product);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ProductResponse detail(String productId){
        Optional<Product> optional = productRepository.findById(productId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Product ID Not Found");
        }

        Product product = optional.get();
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);

        return productResponse;
    }
}
