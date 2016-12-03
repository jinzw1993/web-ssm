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
    public void loginTest1() throws Exception {//0telephone/password/name/email is not allowed to be empty.
        Customer customer = new Customer();

        customer.setTelephone("18182428888");

        customer.setPassword("123");
        Result result = cc.customerLogin(customer);
        System.out.print(result.getStatus());
        System.out.print(result.getMessage());
    }

    @Test
    public void loginTest2() throws Exception {//0telephone/password/name/email is not allowed to be empty.
        Customer customer = new Customer();

        customer.setTelephone("18182428888");
        customer.setEmail("1121762957@qq.com");

        customer.setPassword("123");
        Result result = cc.customerLogin(customer);
        System.out.print(result.getStatus());
        System.out.print(result.getMessage());
    }

    @Test
    public void loginTest3() throws Exception {//0login failed,email or telephone is not exist
        Customer customer = new Customer();
        customer.setTelephone("18182428888");
        customer.setEmail("1121762957@qq.com");
        customer.setPassword("123");
        customer.setName("llll");
        Result result = cc.customerLogin(customer);
        System.out.print(result.getStatus());
        System.out.print(result.getMessage());
    }

    @Test
    public void loginTest4() throws Exception {
        Customer customer = new Customer();
        customer.setPassword("123");
        customer.setName("llll");
        customer.setTelephone("18182428888");
        customer.setEmail("1121762957@qq.com");
        Result result = cc.customerLogin(customer);
        System.out.print(result.getStatus());
        System.out.print(result.getMessage());
    }

    @Test
    public void registerTest1() throws Exception {
        Customer customer = new Customer();
        customer.setTelephone("18182428888");
        customer.setEmail("1121762957@qq.com");
        customer.setPassword("123");
        customer.setName("llll");
        Result result = cc.addCustomer(customer);
        System.out.print(result.getStatus());
        System.out.print(result.getMessage());
    }
}
