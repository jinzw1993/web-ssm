package com.heitian.ssm.dao;

import com.heitian.ssm.model.Customer;
import org.springframework.stereotype.Repository;

/**
 * Created by Lanting on 2016/11/25.
 */
@Repository
public interface CustomerDao {
    Customer getCustomerByTel(Long telephone);
    int addCustomer(Customer customer);
}
