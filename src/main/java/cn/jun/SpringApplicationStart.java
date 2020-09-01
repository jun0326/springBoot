package cn.jun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: jun
 * @Date: 2020/9/1 10:38
 * @Version: 1.0
 * @Description:
 */
@SpringBootApplication
public class SpringApplicationStart {


    public static void main(String[] args) {
        // 第一种启动方式
//        SpringApplication.run(SpringApplicationStart.class,args);

        //第二种启动方式
        SpringApplication springApplication = new SpringApplication(SpringApplicationStart.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
