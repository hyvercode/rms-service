package id.alfaz.rms.service;

import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.CommonUtil;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.ProductGroup;
import id.alfaz.rms.model.request.productGroup.ProductGroupRequest;
import id.alfaz.rms.model.response.productGroup.GetListProductGroupResponse;
import id.alfaz.rms.model.response.productGroup.ProductGroupResponse;
import id.alfaz.rms.repository.ProductGroupRespository;
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
public class ProductGroupService implements BaseService<PageRequest, GetListProductGroupResponse> {

    private ProductGroupRespository productGroupRespository;

    public ProductGroupService(ProductGroupRespository productGroupRespository) {
        this.productGroupRespository = productGroupRespository;
    }

    @Override
    public GetListProductGroupResponse execute(PageRequest input) {
        Page<ProductGroup> page = this.getPageResultByInput(input);
        Set<ProductGroupResponse> productGroupResponses = page.getContent().stream().map(productGroup -> {
            ProductGroupResponse response = new ProductGroupResponse();
            BeanUtils.copyProperties(productGroup,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListProductGroupResponse.builder()
                .content(productGroupResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<ProductGroup> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "groupName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<ProductGroup> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("groupId")) {
            page = productGroupRespository.findByGroupIdAndOutletIdAndActive(pageRequest.getSearchBy(),"root", "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("groupName")){
            page = productGroupRespository.findByGroupNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),"root", "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = productGroupRespository.findByOutletIdAndActive(pageRequest.getSearchBy(),"root", pageable);
        }else{
            page = productGroupRespository.findByOutletIdAndActive("root","Y",pageable);
        }
        return page;
    }

    public ApiResponse add(ProductGroupRequest request){
        ProductGroup productGroup = ProductGroup.builder()
                .groupId(CommonUtil.generateUUIDString())
                .groupName(request.getGroupName())
                .outletId(request.getOutletId())
                .remark(request.getRemark())
                .active(request.getActive())
                .build();
        productGroup.setCreatedBy("system");
        productGroup.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        productGroupRespository.save(productGroup);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String groupId,ProductGroupRequest request){
        Optional<ProductGroup> group = productGroupRespository.findById(groupId);
        if(!group.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Group ID Not Found");
        }

        ProductGroup productGroup = group.get();
        productGroup.setGroupName(request.getGroupName());
        productGroup.setOutletId(request.getOutletId());
        productGroup.setRemark(request.getRemark());
        productGroup.setActive(request.getActive());
        productGroup.setUpdatedBy("system");
        productGroup.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        productGroupRespository.save(productGroup);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ProductGroupResponse detail(String groupId){
        Optional<ProductGroup> group = productGroupRespository.findById(groupId);
        if(!group.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Group ID Not Found");
        }
        ProductGroup productGroup = group.get();
        ProductGroupResponse response = new ProductGroupResponse();
        BeanUtils.copyProperties(productGroup,response);

        return response;
    }
}
