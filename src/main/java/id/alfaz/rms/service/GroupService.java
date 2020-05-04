package id.alfaz.rms.service;

import id.alfaz.rms.helper.context.ApiContext;
import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.CommonUtil;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.Group;
import id.alfaz.rms.model.request.group.GroupRequest;
import id.alfaz.rms.model.response.productGroup.GetListProductGroupResponse;
import id.alfaz.rms.model.response.productGroup.ProductGroupResponse;
import id.alfaz.rms.repository.GroupRespository;
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
public class GroupService implements BaseService<PageRequest, GetListProductGroupResponse> {

    private GroupRespository groupRespository;
    private HttpServletRequest httpServletRequest;

    public GroupService(GroupRespository groupRespository, HttpServletRequest httpServletRequest) {
        this.groupRespository = groupRespository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListProductGroupResponse execute(PageRequest input) {
        Page<Group> page = this.getPageResultByInput(input);
        Set<ProductGroupResponse> productGroupResponses = page.getContent().stream().map(group -> {
            ProductGroupResponse response = new ProductGroupResponse();
            BeanUtils.copyProperties(group,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListProductGroupResponse.builder()
                .content(productGroupResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Group> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "groupName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Group> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("groupId")) {
            page = groupRespository.findByGroupIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("groupName")){
            page = groupRespository.findByGroupNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = groupRespository.findByOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = groupRespository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(GroupRequest request){
        Group group = Group.builder()
                .groupId(CommonUtil.generateUUIDString())
                .groupName(request.getGroupName())
                .outletId(request.getOutletId())
                .remark(request.getRemark())
                .active(request.getActive())
                .build();
        group.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        group.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        groupRespository.save(group);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String groupId, GroupRequest request){
        Optional<Group> group = groupRespository.findById(groupId);
        if(!group.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Group ID Not Found");
        }

        Group productGroup = group.get();
        productGroup.setGroupName(request.getGroupName());
        productGroup.setOutletId(request.getOutletId());
        productGroup.setRemark(request.getRemark());
        productGroup.setActive(request.getActive());
        productGroup.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        productGroup.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        groupRespository.save(productGroup);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ProductGroupResponse detail(String groupId){
        Optional<Group> group = groupRespository.findById(groupId);
        if(!group.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Group ID Not Found");
        }
        Group productGroup = group.get();
        ProductGroupResponse response = new ProductGroupResponse();
        BeanUtils.copyProperties(productGroup,response);

        return response;
    }
}
