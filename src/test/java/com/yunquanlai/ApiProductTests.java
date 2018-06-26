package com.yunquanlai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunquanlai.api.comsumer.ApiProductController;
import com.yunquanlai.utils.R;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Api产品模块单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiProductTests {

	@Autowired
    private ApiProductController apiProductController;

    /**
     * 测试查询商品列表
     */
    @Test
    public void testApiProductQuery() {
        R r = apiProductController.queryProduct(null,null,null,null,10,0,5);
        List list = (List)r.get("productInfoList");
        Assert.assertEquals(list.size(),5);
    }

}
