package io.recruitment.assessment.api;

import io.recruitment.assessment.api.controllers.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.MySqlConfig;
import service.ISqlServerService;
import service.SqlServerServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MySqlConfig mySqlConfig(){
        return new MySqlConfig("db_user", "8b3b91c", "db_user", "8b3b91c7");
    }

    @Bean
    public SqlServerServiceImpl sqlServerServiceImpl(){
        return new SqlServerServiceImpl(mySqlConfig());
    }
    
}
