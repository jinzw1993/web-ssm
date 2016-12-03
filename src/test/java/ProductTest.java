/**
 * Created by Lanting on 2016/11/27.
 */

import com.heitian.ssm.service.ProductService;
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
public class ProductTest {

    @Autowired
    private ProductService ps;

    @Before
    public void setup() {
    }

    @Test
    public void searchProductTest1() throws Exception {

    }



}
