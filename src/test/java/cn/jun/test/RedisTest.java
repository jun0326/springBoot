package cn.jun.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: jun
 * @Date: 2020/9/8 17:38
 * @Version: 1.0
 * @Description:
 */
//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void test01(){
        redisTemplate.opsForValue().set("CD00101","1234");
        System.out.println(redisTemplate.opsForValue().get("CD00101"));
    }

}
