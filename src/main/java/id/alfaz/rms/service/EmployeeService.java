package id.alfaz.rms.service;

import id.alfaz.rms.helper.context.ApiContext;
import id.alfaz.rms.helper.exception.BusinessException;
import id.alfaz.rms.helper.model.ApiResponse;
import id.alfaz.rms.helper.model.PageRequest;
import id.alfaz.rms.helper.service.BaseService;
import id.alfaz.rms.helper.util.CommonUtil;
import id.alfaz.rms.helper.util.PageableUtil;
import id.alfaz.rms.model.entity.Employee;
import id.alfaz.rms.model.request.employee.EmployeeRequest;
import id.alfaz.rms.model.response.employee.EmployeeResponse;
import id.alfaz.rms.model.response.employee.GetListEmployeeResponse;
import id.alfaz.rms.repository.EmployeeRepository;
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
public class EmployeeService implements BaseService<PageRequest, GetListEmployeeResponse> {
    private EmployeeRepository employeeRepository;
    private HttpServletRequest httpServletRequest;

    public EmployeeService(EmployeeRepository employeeRepository, HttpServletRequest httpServletRequest) {
        this.employeeRepository = employeeRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public GetListEmployeeResponse execute(PageRequest input) {
        Page<Employee> page = this.getPageResultByInput(input);
        Set<EmployeeResponse> employeeResponses = page.getContent().stream().map(employee -> {
            EmployeeResponse response = new EmployeeResponse();
            BeanUtils.copyProperties(employee,response);
            return response;
        }).collect(Collectors.toSet());

        return GetListEmployeeResponse.builder()
                .content(employeeResponses)
                .pagination(PageableUtil.pageToPagination(page))
                .build();
    }

    private Page<Employee> getPageResultByInput(PageRequest pageRequest){
        String sortBy = pageRequest.getSortBy()!=null && !pageRequest.getSortBy().isEmpty()? pageRequest.getSortBy() : "employeeCode";
        Pageable pageable =  PageableUtil.createPageRequest(pageRequest,pageRequest.getPageSize(),pageRequest.getPageNumber(),
                sortBy,pageRequest.getSortType());
        Page<Employee> page = null;
        if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("employeeId")) {
            page = employeeRepository.findByEmployeeIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("employeeCode")){
            page = employeeRepository.findByEmployeeCodeAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("firstName")){
            page = employeeRepository.findByFirstNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("lastName")){
            page = employeeRepository.findByLastNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("nickName")){
            page = employeeRepository.findByNickNameIsContainingAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("idCardNumber")){
            page = employeeRepository.findByIdCardNumberAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("phoneNumber")){
            page = employeeRepository.findByPhoneNumberAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("email")){
            page = employeeRepository.findByEmailAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("countryId")){
            page = employeeRepository.findByCountryIdAndOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), "Y", pageable);
        }else if(pageRequest.getSearchBy() !=null && pageRequest.getSortBy().equals("active")){
            page = employeeRepository.findByOutletIdAndActive(pageRequest.getSearchBy(),httpServletRequest.getHeader(ApiContext.outletId), pageable);
        }else{
            page = employeeRepository.findByOutletIdAndActive(httpServletRequest.getHeader(ApiContext.outletId),"Y",pageable);
        }
        return page;
    }

    public ApiResponse add(EmployeeRequest request){
        Employee employee = Employee.builder()
                .employeeId(CommonUtil.generateUUIDString())
                .employeeCode(request.getEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .nickName(request.getNickName())
                .phoneNumber(request.getPhoneNumber())
                .phoneNumber2(request.getPhoneNumber2())
                .email(request.getEmail())
                .countryId(request.getCountryId())
                .provinceId(request.getProvinceId())
                .districtId(request.getDistrictId())
                .subDistrictId(request.getSubDistrictId())
                .villageId(request.getVillageId())
                .cityId(request.getCityId())
                .postcode(request.getPostcode())
                .address(request.getAddress())
                .placeOfBirth(request.getPlaceOfBirth())
                .birthOfDate(request.getBirthOfDate())
                .age(request.getAge())
                .maritalStatus(request.getMaritalStatus())
                .occupationId(request.getOccupationId())
                .sex(request.getSex())
                .idCardType(request.getIdCardType())
                .idCardNumber(request.getIdCardNumber())
                .taxId(request.getTaxId())
                .insuranceId(request.getInsuranceId())
                .insuranceNumber(request.getInsuranceNumber())
                .image(request.getImage())
                .outletId(request.getOutletId())
                .active(request.getActive())
                .build();
        employee.setCreatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        employee.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        employeeRepository.save(employee);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public ApiResponse update(String employeeId,EmployeeRequest request){
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Employee ID Not Found");
        }

        Employee employee = optional.get();
        BeanUtils.copyProperties(request,employee);
        employee.setUpdatedBy(httpServletRequest.getHeader(ApiContext.employeeId));
        employee.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        employeeRepository.save(employee);

        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(CODE_OK)
                .message(PROCESS_SUCCESSFULLY)
                .build();
    }

    public EmployeeResponse detail(String employeeId){
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        if(!optional.isPresent()){
            throw new BusinessException(HttpStatus.CONFLICT,"30020","Employee ID Not Found");
        }

        Employee employee = optional.get();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee,employeeResponse);

        return employeeResponse;

    }
}
