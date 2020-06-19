package id.alfaz.rms.service;

import id.alfaz.rms.helper.context.ApiContext;
import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.CommonUtil;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.Level;
import id.alfaz.rms.model.request.level.LevelRequest;
import id.alfaz.rms.model.response.level.GetListLevelResponse;
import id.alfaz.rms.model.response.level.LevelResponse;
import id.alfaz.rms.repository.LevelRepository;
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
public class LevelService implements BaseService<PageRequest, GetListLevelResponse> {
    private LevelRepository levelRepository;
    private HttpServletRequest httpServletRequest;

    public LevelService(LevelRepository levelRepository, HttpServletRequest httpServletRequest) {
        this.levelRepository = levelRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListLevelResponse execute(PageRequest input) {
        Page<Level> page = this.getPageResultByInput(input);
        Set<LevelResponse> levelResponses = page.getContent().stream().map(level -> {
            LevelResponse response = new LevelResponse();
            BeanUtils.copyProperties(level,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListLevelResponse.builder()
                .content(levelResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Level> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "levelName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Level> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("levelId")) {
            page = levelRepository.findByLevelIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("levelName")){
            page = levelRepository.findByLevelNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = levelRepository.findByOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = levelRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(LevelRequest request){
        Level level = Level.builder()
                .levelId(CommonUtil.generateUUIDString())
                .levelName(request.getLevelName())
                .outletId(request.getOutletId())
                .remark(request.getRemark())
                .active(request.getActive())
                .build();
        level.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        level.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        levelRepository.save(level);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String levelId,LevelRequest request){
        Optional<Level> optional = levelRepository.findById(levelId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Level ID Not Found");
        }
        Level level = optional.get();
        level.setLevelName(request.getLevelName());
        level.setOutletId(request.getOutletId());
        level.setRemark(request.getRemark());
        level.setActive(request.getActive());
        level.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        level.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        levelRepository.save(level);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public LevelResponse detail(String levelId){
        Optional<Level> optional = levelRepository.findById(levelId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Level ID Not Found");
        }

        Level level = optional.get();
        LevelResponse response = new LevelResponse();
        BeanUtils.copyProperties(level,response);

        return response;
    }
}
