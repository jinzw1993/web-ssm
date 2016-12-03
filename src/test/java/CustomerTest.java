/**
 * Created by Lanting on 2016/11/27.
 */

import com.heitian.ssm.bo.Result;
import com.heitian.ssm.model.Customer;
import com.heitian.ssm.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CustomerTest {

    @Autowired
    private CustomerService cc;

    @Before
    public void setup() {
    }

    @Test
    public void loginTest() throws Exception {
        Customer customer = new Customer();
        //customer.setTelephone(18182428888L);
        customer.setPassword("123");
        Result result = cc.customerLogin(customer);
        System.out.print(result.getStatus());
    }

    @Test
    public void registerTest() throws Exception {
        Customer customer = new Customer();
       // customer.setTelephone(18182427777L);
        customer.setPassword("123");
        customer.setName("gouDan");
        Result result = cc.addCustomer(customer);
        System.out.print(result.getStatus());
    }
}
