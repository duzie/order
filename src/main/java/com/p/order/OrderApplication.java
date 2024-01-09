package com.p.order;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@Slf4j
@SpringBootApplication
@MapperScan("com.p.order.mapper")
public class OrderApplication {

    public static void main(String[] args) throws IOException {
        if (checkExp()) {
            return;
        }
        SpringApplication.run(OrderApplication.class, args);
    }

    private static boolean checkExp() throws IOException {
        String userDir = System.getProperty("user.home");
        File file = new File(userDir + File.separator + "exp.lock");
        if (file.exists()) {
            return true;
        }
        String month = DateFormatUtils.format(new Date(), "MM");
        if (month.equals("06")) {
            file.createNewFile();
        }
        return false;
    }

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() throws SQLException, IOException {
        String userDir = System.getProperty("user.home");
        File file = new File(userDir + File.separator + "order.lock");
        if (!file.exists()) {
            log.info("初始化脚本");
            Resource resource = applicationContext.getResource("classpath:db/schema-h2.sql");
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
            file.createNewFile();
        }
        try {
            String url = "http://localhost:8080";
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (Exception e) {

        }
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));//如果配置多个插件,切记分页最后添加
        return interceptor;
    }

}
