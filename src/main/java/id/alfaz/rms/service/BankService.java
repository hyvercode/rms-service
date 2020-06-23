package id.alfaz.rms.service;

import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.Bank;
import id.alfaz.rms.model.projection.bank.BankView;
import id.alfaz.rms.model.request.bank.BankRequest;
import id.alfaz.rms.model.response.bank.BankResponse;
import id.alfaz.rms.model.response.bank.GetListBankResponse;
import id.alfaz.rms.repository.BankRepository;
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
public class BankService implements BaseService<PageRequest, GetListBankResponse> {
    private BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public GetListBankResponse execute(PageRequest input) {
        Page<BankView> bankViews = this.getPageResultByInput(input);

        Set<BankResponse> bankResponses = bankViews.getContent().stream().map(bankView -> BankResponse.builder()
                .bankId(bankView.getBankId())
                .bankName(bankView.getBankName())
                .countryCode(bankView.getCountryCode())
                .bankImageLink(bankView.getBankImageLink())
                .active(bankView.getActive())
        .build()).collect(Collectors.toSet());
        return GetListBankResponse.builder()
                .content(bankResponses)
                .pagination(PageableUtil.pageToPagination(bankViews))
                .build();
    }

    private Page<BankView> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "bankName";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<BankView> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("bankId")){
            page = bankRepository.findByBankId(pageRequest.getSearchBy(),pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("countryCode")) {
            page = bankRepository.findByCountryCode(pageRequest.getSearchBy(),pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("bankName")) {
            page = bankRepository.findByBankName(pageRequest.getSearchBy(),pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")) {
            page = bankRepository.findByActive(pageRequest.getSearchBy(),pageable);
        }else{
            page = bankRepository.findListAll(pageable);
        }

        return page;
    }

    public ApiResponse add(BankRequest input) {
        Bank bank = Bank.builder()
                .bankId(input.getBankId())
                .countryCode(input.getCountryCode())
                .bankName(input.getBankName())
                .bankImageLink(input.getBankImageLink())
                .active(input.getActive())
                .build();
        bank.setCreatedBy("system");
        bank.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        bankRepository.save(bank);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(BankRequest bankRequest) {
        Optional<Bank> bankOptional = bankRepository.findById(bankRequest.getBankId());
        if(!bankOptional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Bank ID Not Found");
        }
        Bank bank = bankOptional.get();
        bank.setBankId(bankRequest.getBankId());
        bank.setCountryCode(bankRequest.getCountryCode());
        bank.setBankName(bankRequest.getBankName());
        bank.setBankImageLink(bankRequest.getBankImageLink());
        bank.setActive(bankRequest.getActive());
        bank.setUpdatedBy("system");
        bank.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        bankRepository.save(bank);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(ConstantResponse.CODE_OK)
                .message(ConstantResponse.PROCESS_SUCCESSFULLY)
                .build();
    }

    public BankResponse detail(String bankId){
        Optional<Bank> bankOptional = bankRepository.findById(bankId);
        if(!bankOptional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Bank ID Not Found");
        }

        Bank bank = bankOptional.get();
        return BankResponse.builder()
                .bankId(bank.getBankId())
                .countryCode(bank.getCountryCode())
                .bankName(bank.getBankName())
                .bankImageLink(bank.getBankImageLink())
                .active(bank.getActive())
                .build();
    }

}
