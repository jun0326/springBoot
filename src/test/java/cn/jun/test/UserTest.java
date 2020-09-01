package cn.jun.test;

import cn.jun.SpringApplicationStart;
import cn.jun.controller.UserController;
import cn.jun.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jun
 * @Date: 2020/9/1 15:27
 * @Version: 1.0
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplicationStart.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserTest {

    @Autowired
    UserController userController;

    @Test
    public void test1() {
        final String CONTENT_TYPE_TEXT_JSON = "text/json";
        final String URL = "http://localhost:8080/user/get";
//        final String URL = "http://172.21.39.253:8080/user/get";
        final String json = "{\n" +
                "\"userId\": 1000,\n" +
                "\"userName\" : \"jun\",\n" +
                "\"password\": \"113113\",\n" +
                "\"phone\": \"1576\"\n" +
                "}";
        CloseableHttpClient httpClient = HttpClients.createDefault();

//        HttpGet httpGet = new HttpGet(URL);
        HttpPost httpPost = new HttpPost(URL);
//        httpPost.setHeader("HTTP Method","POST");
        httpPost.setHeader("Connection","Keep-Alive");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");

        try {
            StringEntity stringEntity = new StringEntity(json);
            stringEntity.setContentType(CONTENT_TYPE_TEXT_JSON);
            httpPost.setEntity(stringEntity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            String s = EntityUtils.toString(entity, "utf-8");

            System.out.println("-------------"+s);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2(){
//        final String CONTENT_TYPE_TEXT_JSON = "text/json";
        final String URL = "http://localhost:8080/user/get/1000";

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(URL);
//        httpGet.setHeader("HTTP Method","GET");
        httpGet.setHeader("Connection","Keep-Alive");
        httpGet.setHeader("Content-Type", "application/json;charset=utf-8");

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            String s = EntityUtils.toString(entity, "utf-8");

            System.out.println("-------------"+s);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3(){

        System.out.println("==================" + userController + "===================");
        User user = userController.selectByPrimaryKey(1000);
        System.out.println(user);
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/get/1000");


        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    private WebApplicationContext context;

    private ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testSend() throws Exception {
        Integer id =1000;
        //调用接口
        mockMvc.perform(MockMvcRequestBuilders.get("/user/get")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(id)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void test4() {
        final String CONTENT_TYPE_TEXT_JSON = "text/json";
        final String URL = "http://localhost:8080/user/get";
//        final String URL = "http://172.21.39.253:8080/user/get";
        final String json = "{\n" +
                "\"userId\": 1000,\n" +
                "\"userName\" : \"jun\",\n" +
                "\"password\": \"113113\",\n" +
                "\"phone\": \"1576\"\n" +
                "}";
        CloseableHttpClient httpClient = HttpClients.createDefault();

//        HttpGet httpGet = new HttpGet(URL);
//        HttpPost httpPost = new HttpPost(URL);
//        httpPost.setHeader("HTTP Method","POST");
//        httpGet.setHeader("Connection","Keep-Alive");
//        httpGet.setHeader("Content-Type", "application/json;charset=utf-8");


        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("json", json));
            String url1 = URL+"" + URLEncodedUtils.format(params, "utf-8");



            HttpGet httpGet = new HttpGet(URL);
            httpGet.setHeader("Connection","Keep-Alive");
            httpGet.setHeader("Content-Type", "application/json;charset=utf-8");



            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            String s = EntityUtils.toString(entity, "utf-8");

            System.out.println("-------------"+s);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
