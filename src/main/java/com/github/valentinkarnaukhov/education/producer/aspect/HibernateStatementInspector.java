package com.github.valentinkarnaukhov.education.producer.aspect;

import lombok.RequiredArgsConstructor;
import org.hibernate.resource.jdbc.spi.StatementInspector;

@RequiredArgsConstructor
public class HibernateStatementInspector implements StatementInspector {

    private final DbRequestAnalyzerManager dbRequestAnalyzerManager;

    @Override
    public String inspect(String sql) {
        dbRequestAnalyzerManager.incrementRequestCounterForCurrentThread();
        return sql;
    }

}
