package io.recruitment.assessment.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.MySqlConfig;
import service.SqlServerServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MySqlConfig mySqlConfig(){
        return new MySqlConfig("jdbc:mysql://localhost:3306/assessment", "db_user", "8b3b91c7");
    }

    @Bean
    public SqlServerServiceImpl sqlServerServiceImpl(){
        return new SqlServerServiceImpl(mySqlConfig());
    }
    
}
