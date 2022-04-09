package com.hyvercode.rms.service;

import com.hyvercode.rms.helper.context.ApiContext;
import com.hyvercode.rms.helper.exception.BusinessException;
import com.hyvercode.rms.helper.model.ApiResponse;
import com.hyvercode.rms.helper.model.PageRequest;
import com.hyvercode.rms.helper.service.BaseService;
import com.hyvercode.rms.helper.util.CommonUtil;
import com.hyvercode.rms.helper.util.PageableUtil;
import com.hyvercode.rms.model.entity.Customer;
import com.hyvercode.rms.model.request.customer.CustomerRequest;
import com.hyvercode.rms.model.response.customer.CustomerResponse;
import com.hyvercode.rms.model.response.customer.GetListCustomerResponse;
import com.hyvercode.rms.repository.CustomerRepository;
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
public class CustomerService implements BaseService<PageRequest, GetListCustomerResponse> {

    private CustomerRepository customerRepository;
    private HttpServletRequest httpServletRequest;


    public CustomerService(CustomerRepository customerRepository, HttpServletRequest httpServletRequest) {
        this.customerRepository = customerRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListCustomerResponse execute(PageRequest input) {
        Page<Customer> page = this.getPageResultByInput(input);
        Set<CustomerResponse> CustomerResponses = page.getContent().stream().map(customer -> {
            CustomerResponse response = new CustomerResponse();
            BeanUtils.copyProperties(customer,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListCustomerResponse.builder()
                .content(CustomerResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Customer> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "customerId";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Customer> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("customerId")) {
            page = customerRepository.findByCustomerIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("firstName")){
            page = customerRepository.findByFirstNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("lastName")){
            page = customerRepository.findByLastNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("nickName")){
            page = customerRepository.findByNickNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("idCardNumber")){
            page = customerRepository.findByIdCardNumberAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("phoneNumber")){
            page = customerRepository.findByPhoneNumberAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("email")){
            page = customerRepository.findByEmailAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("countryId")){
            page = customerRepository.findByCountryIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = customerRepository.findByOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = customerRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(CustomerRequest request){
        Customer customer = new Customer();
        BeanUtils.copyProperties(request,customer);
        customer.setCustomerId(CommonUtil.generateUUIDString());
        customer.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        customer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        customerRepository.save(customer);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String customerId,CustomerRequest request){

        Optional<Customer> optional = customerRepository.findById(customerId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Customer ID Not Found");
        }

        Customer customer = optional.get();
        BeanUtils.copyProperties(request,customer);
        customer.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        customer.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        customerRepository.save(customer);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public CustomerResponse detail(String customerId){
        Optional<Customer> optional = customerRepository.findById(customerId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Customer ID Not Found");
        }

        Customer customer = optional.get();
        CustomerResponse customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customer,customerResponse);

        return customerResponse;
    }
}
