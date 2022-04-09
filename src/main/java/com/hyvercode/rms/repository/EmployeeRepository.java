package com.hyvercode.rms.repository;

import com.hyvercode.rms.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {

    Page<Employee> findByEmployeeIdAndOutletIdAndActive(String employeeId,String outletId, String active,Pageable pageable);
    Page<Employee> findByEmployeeCodeAndOutletIdAndActive(String employeeCode,String outletId, String active,Pageable pageable);
    Page<Employee> findByFirstNameIsContainingAndOutletIdAndActive(String firstNameName,String outletId, String active,Pageable pageable);
    Page<Employee> findByLastNameIsContainingAndOutletIdAndActive(String nickNameName,String outletId, String active,Pageable pageable);
    Page<Employee> findByNickNameIsContainingAndOutletIdAndActive(String lastNameName,String outletId, String active,Pageable pageable);
    Page<Employee> findByIdCardNumberAndOutletIdAndActive(String idCardNumber,String outletId, String active,Pageable pageable);
    Page<Employee> findByEmailAndOutletIdAndActive(String email,String outletId, String active,Pageable pageable);
    Page<Employee> findByPhoneNumberAndOutletIdAndActive(String phoneNumber,String outletId, String active,Pageable pageable);
    Page<Employee> findByCountryIdAndOutletIdAndActive(String countryId,String outletId, String active,Pageable pageable);
    Page<Employee> findByOutletIdAndActive(String outletId,String active, Pageable pageable);
}
