package id.alfaz.rms.service;

import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.projection.village.PostcodeView;
import id.alfaz.rms.model.response.postcode.GetListPostcodeResponse;
import id.alfaz.rms.model.response.postcode.PostcodeResponse;
import id.alfaz.rms.repository.VillageRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostcodeService implements BaseService<PageRequest, GetListPostcodeResponse> {
    private VillageRespository villageRespository;

    public PostcodeService(VillageRespository villageRespository) {
        this.villageRespository = villageRespository;
    }

    @Override
    public GetListPostcodeResponse execute(PageRequest input) {
        Page<PostcodeView> postcodeViews = this.getPageResultByInput(input);
        Set<PostcodeResponse> postcodeResponses = postcodeViews.getContent().stream().map(postcode -> PostcodeResponse.builder()
                .postcode(postcode.getPostcode())
                .areaName(postcode.getVillageName())
                .build()
        ).collect(Collectors.toSet());

        return GetListPostcodeResponse.builder()
                .content(postcodeResponses)
                .pagination(PageableUtil.pageToPagination(postcodeViews))
                .build();
    }

    public Page<PostcodeView> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "subDistrictId";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<PostcodeView>  page=null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("postcode")) {
            page = villageRespository.getListPostCode(Integer.parseInt(pageRequest.getSearchBy()),pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("postcodeName")){
            page = villageRespository.getListPostCodeName(pageRequest.getSearchBy(),pageable);
        }else{
            page = villageRespository.getListPostCodeName(pageRequest.getSearchBy(),pageable);
        }
        return page;
    }

    public PostcodeResponse findByPostcode(Integer postcode){
        Optional<PostcodeView> optional =villageRespository.getPostCode(postcode);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Postcode Not Found");
        }
         return PostcodeResponse.builder()
                 .postcode(optional.get().getPostcode())
                 .areaName(optional.get().getVillageName())
                 .build();
    }

    public PostcodeResponse findByPostcodeName(String postcodeName){
        Optional<PostcodeView> optional =villageRespository.getPostCodeName(postcodeName);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Area Not Found");
        }

        return PostcodeResponse.builder()
                .postcode(optional.get().getPostcode())
                .areaName(optional.get().getVillageName())
                .build();
    }
}
