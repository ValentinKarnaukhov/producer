package com.github.valentinkarnaukhov.education.producer.config;

import com.github.valentinkarnaukhov.education.producer.aspect.DbRequestAnalyzerManager;
import com.github.valentinkarnaukhov.education.producer.aspect.HibernateStatementInspector;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HibernateConfig {

    private final DbRequestAnalyzerManager dbRequestAnalyzerManager;

    @Bean
    public HibernatePropertiesCustomizer configure() {
        return hibernateProperties -> hibernateProperties.put(AvailableSettings.STATEMENT_INSPECTOR, new HibernateStatementInspector(dbRequestAnalyzerManager));
    }

}
