package com.hyvercode.rms.service;

import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Category;
import com.hyvercode.rms.model.request.category.CategoryRequest;
import com.hyvercode.rms.model.response.productCategory.GetListProductCategoryResponse;
import com.hyvercode.rms.model.response.productCategory.ProductCategoryResponse;
import com.hyvercode.rms.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CategoryService implements BaseService<PageRequest, GetListProductCategoryResponse> {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    private CategoryRepository categoryRepository;
    private HttpServletRequest httpServletRequest;

    public CategoryService(CategoryRepository categoryRepository, HttpServletRequest httpServletRequest) {
        this.categoryRepository = categoryRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListProductCategoryResponse execute(PageRequest input) {

        Page<Category> page = this.getPageResultByInput(input);
        Set<ProductCategoryResponse> categoryResponses = page.getContent().stream().map(category -> {
            ProductCategoryResponse response = new ProductCategoryResponse();
            BeanUtils.copyProperties(category,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListProductCategoryResponse.builder()
                .content(categoryResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Category> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "categoryName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Category> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("categoryId")) {
            page = categoryRepository.findByCategoryIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("categoryName")){
            page = categoryRepository.findByCategoryNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("groupId")){
            page = categoryRepository.findByGroupIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = categoryRepository.findByOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = categoryRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(CategoryRequest request){
        Category category = Category.builder()
                .categoryId(CommonUtil.generateUUIDString())
                .categoryName(request.getCategoryName())
                .groupId(request.getGroupId())
                .outletId(request.getOutletId())
                .active(request.getActive())
                .remark(request.getRemark())
                .build();
        category.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        categoryRepository.save(category);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String categoryId, CategoryRequest request){
        Optional<Category> optional = categoryRepository.findById(categoryId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Category ID Not Found");
        }

        Category category = optional.get();
        category.setCategoryName(request.getCategoryName());
        category.setGroupId(request.getGroupId());
        category.setOutletId(request.getOutletId());
        category.setActive(request.getActive());
        category.setRemark(request.getRemark());
        category.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        categoryRepository.save(category);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ProductCategoryResponse detail(String categoryId){
        Optional<Category> optional = categoryRepository.findById(categoryId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Category ID Not Found");
        }

        Category category = optional.get();
        ProductCategoryResponse response = new ProductCategoryResponse();
        BeanUtils.copyProperties(category,response);

        return response;
    }
}
