//package com.clone.fbclone.config;
//
//import com.zaxxer.hikari.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.*;
//import javax.sql.DataSource;
//import java.net.URISyntaxException;
//import org.apache.commons.dbcp2.BasicDataSource;
//
//@Configuration
//public class MainConfig {
//
//    @Bean
//    public BasicDataSource dataSource() throws URISyntaxException {
//        String dbUrl = System.getenv("JDBC_DATABASE_URL");
//        String username = System.getenv("JDBC_DATABASE_USERNAME");
//        String password = System.getenv("JDBC_DATABASE_PASSWORD");
//
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(dbUrl);
//        basicDataSource.setUsername(username);
//        basicDataSource.setPassword(password);
//
//        return basicDataSource;
//    }
//}
