package id.alfaz.rms.service;

import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.CommonUtil;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.CurrencyRate;
import id.alfaz.rms.model.request.currencyRate.CurrencyRateRequest;
import id.alfaz.rms.model.response.currencyRate.CurrencyRateResponse;
import id.alfaz.rms.model.response.currencyRate.GetListCurrencyRateResponse;
import id.alfaz.rms.repository.CurrencyRateRepository;
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
public class CurrencyRateService implements BaseService<PageRequest, GetListCurrencyRateResponse> {

    private CurrencyRateRepository currencyRateRepository;

    public CurrencyRateService(CurrencyRateRepository currencyRateRepository) {
        this.currencyRateRepository = currencyRateRepository;
    }


    @Override
    public GetListCurrencyRateResponse execute(PageRequest input) {
        Page<CurrencyRate> page = this.getPageResultByInput(input);
        Set<CurrencyRateResponse> currencyRateResponses = page.getContent().stream().map(currencyRate -> {
            CurrencyRateResponse response = new CurrencyRateResponse();
            BeanUtils.copyProperties(currencyRate,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListCurrencyRateResponse.builder()
                .content(currencyRateResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<CurrencyRate> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "currencyId";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<CurrencyRate> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("currencyRateId")) {
            page = currencyRateRepository.findByCurrencyRateIdAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("dateRate")){
            page = currencyRateRepository.findByDateRateAndActive(Timestamp.valueOf(pageRequest.getSearchBy()), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("currencyId")){
            page = currencyRateRepository.findByCurrencyIdIsContainingAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = currencyRateRepository.findByActive(pageRequest.getSearchBy(), pageable);
        }else{
            page = currencyRateRepository.findByActive("Y",pageable);
        }
        return page;
    }

    public ApiResponse add(CurrencyRateRequest request){
        CurrencyRate  currencyRate = CurrencyRate.builder()
                .currencyRateId(CommonUtil.generateUUIDString())
                .dateRate(Timestamp.valueOf(request.getDateRate()))
                .currencyId(request.getCurrencyId())
                .currencyRate(request.getCurrencyRate())
                .active(request.getActive())
                .build();
        currencyRate.setCreatedBy("system");
        currencyRate.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        currencyRateRepository.save(currencyRate);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public CurrencyRateResponse detail(String currencyRateId){
        Optional<CurrencyRate> optional = currencyRateRepository.findById(currencyRateId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Currency Rate ID Not Found");
        }
        CurrencyRateResponse currencyRateResponse = new CurrencyRateResponse();
        BeanUtils.copyProperties(optional.get(),currencyRateResponse);

        return currencyRateResponse;
    }
}
