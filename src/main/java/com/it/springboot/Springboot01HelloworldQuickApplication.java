package com.it.springboot;

import com.it.springboot.util.InitAppcationPropertyUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@MapperScan(value = "com.it.springboot.mapper") //扫描mapper文件
//@EnableAutoConfiguration
@SpringBootApplication
public class Springboot01HelloworldQuickApplication {

    public static void main(String[] args) throws UnknownHostException {
        Logger logger = LoggerFactory.getLogger(Springboot01HelloworldQuickApplication.class);
        logger.info("SpringBoot启动开始......");

        String[] res = InitAppcationPropertyUtil.readOuter(args);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Springboot01HelloworldQuickApplication.class, res);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        logger.info("======{}",environment.getProperty("person.name"));
        String applicationName = environment.getProperty("spring.application.name");
        String port = environment.getProperty("server.port");
        String addr = InetAddress.getLocalHost().getHostAddress();
        logger.info("\n-------------------------------------------\n\t"
                +"Application '{}' is running ! Accessing URLs Local is http://localhost:{} +\n\t"
                +"External is http;//{}:{}",applicationName,port,addr,port
        );

    }

}
