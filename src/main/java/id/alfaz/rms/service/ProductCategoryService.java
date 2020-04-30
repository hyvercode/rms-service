package id.alfaz.rms.service;

import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.CommonUtil;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.ProductCategory;
import id.alfaz.rms.model.request.productCategory.ProductCategoryRequest;
import id.alfaz.rms.model.response.productCategory.GetListProductCategoryResponse;
import id.alfaz.rms.model.response.productCategory.ProductCategoryResponse;
import id.alfaz.rms.repository.ProductCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static id.alfaz.rms.helper.util.ConstantResponse.CODE_OK;
import static id.alfaz.rms.helper.util.ConstantResponse.PROCESS_SUCCESSFULLY;

@Service
public class ProductCategoryService implements BaseService<PageRequest, GetListProductCategoryResponse> {
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public GetListProductCategoryResponse execute(PageRequest input) {
        Page<ProductCategory> page = this.getPageResultByInput(input);
        Set<ProductCategoryResponse> categoryResponses = page.getContent().stream().map(productCategory -> {
            ProductCategoryResponse response = new ProductCategoryResponse();
            BeanUtils.copyProperties(productCategory,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListProductCategoryResponse.builder()
                .content(categoryResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<ProductCategory> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "categoryName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<ProductCategory> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("categoryId")) {
            page = productCategoryRepository.findByCategoryIdAndOutletIdAndActive(pageRequest.getSearchBy(),"root", "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("categoryName")){
            page = productCategoryRepository.findByCategoryNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),"root", "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("groupId")){
            page = productCategoryRepository.findByGroupIdAndOutletIdAndActive(pageRequest.getSearchBy(),"root", "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = productCategoryRepository.findByOutletIdAndActive(pageRequest.getSearchBy(),"root", pageable);
        }else{
            page = productCategoryRepository.findByOutletIdAndActive("root","Y",pageable);
        }
        return page;
    }

    public ApiResponse add(ProductCategoryRequest request){
        ProductCategory productCategory = ProductCategory.builder()
                .categoryId(CommonUtil.generateUUIDString())
                .categoryName(request.getCategoryName())
                .groupId(request.getGroupId())
                .outletId(request.getOutletId())
                .active(request.getActive())
                .build();
        productCategory.setCreatedBy("system");
        productCategory.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        productCategoryRepository.save(productCategory);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String categoryId,ProductCategoryRequest request){
        Optional<ProductCategory> optional = productCategoryRepository.findById(categoryId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Category ID Not Found");
        }

        ProductCategory productCategory = optional.get();
        productCategory.setCategoryName(request.getCategoryName());
        productCategory.setGroupId(request.getGroupId());
        productCategory.setOutletId(request.getOutletId());
        productCategory.setActive(request.getActive());
        productCategory.setUpdatedBy("system");
        productCategory.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        productCategoryRepository.save(productCategory);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ProductCategoryResponse detail(String categoryId){
        Optional<ProductCategory> optional = productCategoryRepository.findById(categoryId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Category ID Not Found");
        }

        ProductCategory productCategory = optional.get();
        ProductCategoryResponse response = new ProductCategoryResponse();
        BeanUtils.copyProperties(productCategory,response);

        return response;
    }
}
