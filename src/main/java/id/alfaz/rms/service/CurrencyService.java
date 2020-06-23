package id.alfaz.rms.service;

import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.Currency;
import id.alfaz.rms.model.request.currency.CurrencyRequest;
import id.alfaz.rms.model.response.currency.CurrencyResponse;
import id.alfaz.rms.model.response.currency.GetListCurrencyResponse;
import id.alfaz.rms.repository.CurrencyRepository;
import id.alfaz.rms.helper.util.ConstantResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CurrencyService implements BaseService<PageRequest, GetListCurrencyResponse> {
    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public GetListCurrencyResponse execute(PageRequest input) {
       Page<Currency> page = this.getPageResultByInput(input);
        Set<CurrencyResponse> currencyResponses = page.getContent().stream().map(currency -> CurrencyResponse.builder()
                .currencyId(currency.getCurrencyId())
                .currencyName(currency.getCurrencyName())
                .currencySymbol(currency.getCurrencySymbol())
                .active(currency.getActive())
                .build()).collect(Collectors.toSet());

        return GetListCurrencyResponse.builder()
                .content(currencyResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Currency> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "currencyId";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Currency> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("currencyId")) {
            page = currencyRepository.findByCurrencyIdAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("currencyName")){
            page = currencyRepository.findByCurrencyNameIsContainingAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("currencySymbol")){
            page = currencyRepository.findByCurrencySymbolAndActive(pageRequest.getSearchBy(), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = currencyRepository.findByActive(pageRequest.getSearchBy(), pageable);
        }else{
            page = currencyRepository.findByActive("Y",pageable);
        }
        return page;
    }

    public ApiResponse add(CurrencyRequest request){
        Currency currency = Currency.builder()
                .currencyId(request.getCurrencyId())
                .currencyName(request.getCurrencyName())
                .currencySymbol(request.getCurrencySymbol())
                .active(request.getActive())
                .build();
        currency.setCreatedBy("system");
        currency.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        currencyRepository.save(currency);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String currencyId,CurrencyRequest request){
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if(!optionalCurrency.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Currency ID Not Found");
        }

        Currency currency = optionalCurrency.get();
        currency.setCurrencyId(request.getCurrencyId());
        currency.setCurrencyName(request.getCurrencyName());
        currency.setCurrencySymbol(request.getCurrencySymbol());
        currency.setActive(request.getActive());
        currency.setUpdatedBy("system");
        currency.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        currencyRepository.save(currency);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public CurrencyResponse detail(String currencyId){
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if(!optionalCurrency.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Currency ID Not Found");
        }

        Currency currency = optionalCurrency.get();

        return CurrencyResponse.builder()
                .currencyId(currency.getCurrencyId())
                .currencyName(currency.getCurrencyName())
                .currencySymbol(currency.getCurrencySymbol())
                .active(currency.getActive())
                .build();
    }
}
