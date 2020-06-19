package id.alfaz.rms.repository;

import id.alfaz.rms.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    Page<Customer> findByCustomerIdAndOutletIdAndActive(String customerId,String outletId, String active, Pageable pageable);
    Page<Customer> findByFirstNameIsContainingAndOutletIdAndActive(String firstNameName,String outletId, String active,  Pageable pageable);
    Page<Customer> findByLastNameIsContainingAndOutletIdAndActive(String nickNameName,String outletId, String active,Pageable pageable);
    Page<Customer> findByNickNameIsContainingAndOutletIdAndActive(String lastNameName,String outletId, String active,Pageable pageable);
    Page<Customer> findByIdCardNumberAndOutletIdAndActive(String idCardNumber,String outletId, String active, Pageable pageable);
    Page<Customer> findByEmailAndOutletIdAndActive(String email,String outletId, String active,Pageable pageable);
    Page<Customer> findByPhoneNumberAndOutletIdAndActive(String phoneNumber,String outletId, String active,Pageable pageable);
    Page<Customer> findByCountryIdAndOutletIdAndActive(String countryId,String outletId, String active,Pageable pageable);
    Page<Customer> findByOutletIdAndActive(String outletId,String active,Pageable pageable);
}
