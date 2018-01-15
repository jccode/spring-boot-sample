package com.github.jccode.springcloud.integrateddemo.order.service;

import com.github.jccode.springcloud.integrateddemo.account.api.AccountAPI;
import com.github.jccode.springcloud.integrateddemo.order.model.Order;
import com.github.jccode.springcloud.integrateddemo.user.api.UserAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@MybatisTest
@Import(OrderService.class)
// Ugly fixed EnableFeignClient annotation. https://stackoverflow.com/questions/43093968/enablefeignclients-and-feignclient-fail-on-autowiring-feigncontext-nosuchbea
@ImportAutoConfiguration({RibbonAutoConfiguration.class, FeignRibbonClientAutoConfiguration.class, FeignAutoConfiguration.class})
@ActiveProfiles("test")
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean(name = "accountAPI")
    private AccountAPI accountClient;

    @MockBean(name = "userAPI")
    private UserAPI userClient;

    @Test
    public void mybatisWorks() {
        Order order = new Order();
        order.setUserId(1);
        order.setPayment(100);
        order.setStatus(0);
        assertThat(order.getId(), is(nullValue()));

        int insertRet = orderService.save(order);
        assertThat(insertRet, greaterThan(0));
        assertThat(order.getId(), greaterThan(0));
    }
}
