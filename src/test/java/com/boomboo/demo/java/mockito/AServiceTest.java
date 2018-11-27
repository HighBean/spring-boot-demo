package com.boomboo.demo.java.mockito;

import com.alibaba.fastjson.JSONObject;
import com.boomboo.demo.DemoApplication;
import com.boomboo.demo.service.ElasticSearchService;
import com.boomboo.demo.service.wrapper.AServiceAImpl;
import com.boomboo.demo.service.wrapper.AServiceWrapper;
import com.boomboo.demo.service.wrapper.AbstractAService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class AServiceTest extends SampleBaseTestCase {


    /**
     * @InjectMocks的问题是：如果被测试类是代理类，那么注入会失效。比如上面的UserService如果是事务或者其他AOP代理类，那么进入@Test方法时UserService中的DAO属性不会被Mock类替换。 代理类如何注入
     */
    @InjectMocks
    @Resource(name = "aService1")
    private AServiceWrapper aServiceWrapper;

    @Mock
    private ElasticSearchService elasticSearchService;

    @Mock
    private AbstractAService abstractAService;

    @Test
    public void testDoA() {
        doNothing().when(elasticSearchService).postData("", new JSONObject(), "");
        when(abstractAService.support(anyString())).thenReturn(true);
        doNothing().when(abstractAService).executeA();
        aServiceWrapper.doA("A");
    }

}
