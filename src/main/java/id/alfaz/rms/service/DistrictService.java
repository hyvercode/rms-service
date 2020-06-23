package id.alfaz.rms.service;

import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.CommonUtil;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.District;
import id.alfaz.rms.model.request.district.DistrictRequest;
import id.alfaz.rms.model.response.district.DistrictResponse;
import id.alfaz.rms.model.response.district.GetListDistrictResponse;
import id.alfaz.rms.repository.DistrictRespository;
import id.alfaz.rms.helper.util.ConstantResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DistrictService implements BaseService<PageRequest, GetListDistrictResponse> {
    private DistrictRespository districtRespository;

    public DistrictService(DistrictRespository districtRespository) {
        this.districtRespository = districtRespository;
    }

    @Override
    public GetListDistrictResponse execute(PageRequest input) {
        Page<District> page = this.getPageResultByInput(input);
        Set<DistrictResponse> districtResponses=page.getContent().stream().map(district -> {
            DistrictResponse response = new DistrictResponse();
            BeanUtils.copyProperties(district,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListDistrictResponse.builder()
                .content(districtResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    public Page<District> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "districtId";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<District>  page=null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("districtId")) {
            page = districtRespository.findByDistrictIdAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("districtName")){
            page = districtRespository.findByDistrictNameIsContainingAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("districtCode")){
            page = districtRespository.findByDistrictCodeAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("provinceCode")){
            page = districtRespository.findByProvinceCodeAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = districtRespository.findByActive(pageRequest.getSearchBy(), pageable);
        }else{
            page = districtRespository.findByActive("Y",pageable);
        }
        return page;
    }

    public ApiResponse add(DistrictRequest districtRequest){
        District district = District.builder()
                .districtId(CommonUtil.generateUUIDString())
                .districtCode(districtRequest.getDistrictCode())
                .districtName(districtRequest.getDistrictName())
                .provinceCode(districtRequest.getProvinceCode())
                .active(districtRequest.getActive())
                .build();
        district.setCreatedBy("system");
        district.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        districtRespository.save(district);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String districtId,DistrictRequest districtRequest){
        Optional<District> optionalDistrict =districtRespository.findById(districtId);
        if(!optionalDistrict.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","District ID Not Found");
        }
        District district = optionalDistrict.get();
        district.setDistrictCode(districtRequest.getDistrictCode());
        district.setDistrictName(districtRequest.getDistrictName());
        district.setProvinceCode(districtRequest.getProvinceCode());
        district.setActive(districtRequest.getActive());
        district.setUpdatedBy("system");
        district.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        districtRespository.save(district);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public DistrictResponse detail(String districtId){
        Optional<District> optionalDistrict =districtRespository.findById(districtId);
        if(!optionalDistrict.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","District ID Not Found");
        }
        District  district= optionalDistrict.get();
        DistrictResponse districtResponse = new DistrictResponse();
        BeanUtils.copyProperties(district,districtResponse);
        return districtResponse;
    }
}
