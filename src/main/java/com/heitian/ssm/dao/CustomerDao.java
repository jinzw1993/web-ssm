package com.heitian.ssm.dao;

import com.heitian.ssm.model.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Lanting on 2016/11/25.
 */
@Repository
public interface CustomerDao {
    Customer getCustomerByTel(String telephone);
    Customer getCustomerByEmail(String email);
    Customer getCustomerById(Long id);
    int activateCustomerEmail(@Param("telephone") String telephone, @Param("email") String email);
    int addCustomer(Customer customer);
    
    int updateBalance(@Param("balance") Double balance, @Param("email") String email);
}