package cn.jun.common.utils;

import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * get 查询 post 修改 put 新增 delete 请求
 *
 * @Author: jun
 * @Date: 2020/9/2 9:31
 * @Version: 1.0
 * @Description:
 */
public class HttpClientUtil {

    /**
     * 带参数的get请求
     *
     * @param url
     * @param param
     * @return String
     */
    public static String doGet(String url, Map<String, String> param) {

        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;

        try {
            //创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            //创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            //执行请求
            response = httpClient.execute(httpGet);

            //判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close(httpClient, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的get请求
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, String> param) {

        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //定义response对象
        CloseableHttpResponse response = null;
        //定义返回对象
        String resultString = "";
        //创建uri
        try {
            URIBuilder builder = new URIBuilder(url);
            for (String key : param.keySet()) {
                builder.addParameter(key, param.get(key));
            }
            URI uri = builder.build();

            //创建HttpPost对象
            HttpPost httpPost = new HttpPost(uri);
            //执行请求
            response = httpClient.execute(httpPost);
            //判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close(httpClient, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的post请求
     *
     * @param url
     * @return
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * 带json格式的post请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPostJson(String url, String json) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //定义response对象
        CloseableHttpResponse response = null;
        //定义返回值
        String resultString = "";

        //创建HttpPost对象
        HttpPost httpPost = new HttpPost(url);
        //创建请求内容
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);

        try {
            //执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 带参数的put请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doPut(String url, Map<String, String> param) {

        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //定义reponse对象
        CloseableHttpResponse response = null;

        //定义返回值对象
        String resultString = "";

        try {
            //创建URI
            URIBuilder builder = new URIBuilder(url);
            for (String key : param.keySet()) {
                builder.addParameter(key, param.get(key));
            }
            URI uri = builder.build();
            //创建httpput对象
            HttpPut httpPut = new HttpPut(uri);

            //执行请求
            response = httpClient.execute(httpPut);

            resultString = EntityUtils.toString(response.getEntity(), "utf-8");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close(httpClient, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的put请求
     *
     * @param url
     * @return
     */
    public static String doPut(String url) {
        return doPut(url, null);
    }

    /**
     * 带json格式参数的put请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String doJsonPut(String url, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        String resultString = null;

        HttpPut httpPut = new HttpPut(url);
        //创建entity
        StringEntity stringEntity = new StringEntity(json, "utf-8");
        httpPut.setEntity(stringEntity);

        try {
            response = httpClient.execute(httpPut);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 带参的delete请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doDelete(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        String resultString = "";

        try {
            URIBuilder builder = new URIBuilder(url);
            for (String key : param.keySet()) {
                builder.addParameter(key, param.get(key));
            }
            URI uri = builder.build();
            HttpDelete httpDelete = new HttpDelete(uri);
            response = httpClient.execute(httpDelete);

            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close(httpClient, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 无参数的delete请求
     *
     * @param url
     * @return
     */
    public static String doDelete(String url) {
        return doDelete(url, null);
    }

    /**
     * 关闭资源
     *
     * @param httpClient
     * @param response
     * @throws IOException
     */
    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse response) throws IOException {
        if (response != null)
            response.close();
        httpClient.close();
    }
}
