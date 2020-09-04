package cn.jun.test;

import cn.jun.SpringApplicationStart;
import cn.jun.common.utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author: jun
 * @Date: 2020/9/1 15:27
 * @Version: 1.0
 * @Description:
 */

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SpringApplicationStart.class)  // 开启启动类
// 上面的注解一起使用
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class UserTest {

    @Autowired
    private WebApplicationContext context;

    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

//    @Before
//    public void setupMockMvc() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }

    /**
     * spring boot自带的 Mock测试单元
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap();
        params.add("userNmae","jun01");
        //创建request
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/user/selectByUserName")
                .contentType(MediaType.APPLICATION_JSON_UTF8).params(params);
        MvcResult mvcResult = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        Assert.assertTrue("正确",status == 200);
        Assert.assertTrue("错误",status != 200);
    }

    /**
     * 调用HttpClientUtil工具类
     */
    @Test
    public void test02() {
        final String url = "http://localhost:8080/user/selectByName";
        final String json = "{\n" +
                "\"userId\": 1000,\n" +
                "\"userName\" : \"jun\",\n" +
                "\"password\": \"113113\",\n" +
                "\"phone\": \"1576\"\n" +
                "}";
        String resultString = HttpClientUtil.doPostJson(url, json);
        System.out.println(resultString);
    }

    /**
     * 请求数据时json格式
     */
    @Test
    public void test03() throws Exception {

        Map<String,String> map = new HashMap();
        map.put("userId","1000");
        map.put("userNmae","jun");

        String json = JSON.toJSONString(map);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/selectByName")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
        MvcResult mvcResult = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        Assert.assertTrue("正确",status == 200);

    }

}
