package com.github.valentinkarnaukhov.education.producer.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Aspect
public class RepositoryAspect {

    private final DbRequestAnalyzerManager dbRequestAnalyzerManager;

    @Around(value = "target(com.github.valentinkarnaukhov.education.producer.repository.CompanyRepository)")
    public Object repository(ProceedingJoinPoint pjp) throws Throwable {
        dbRequestAnalyzerManager.start();
        var result = pjp.proceed();
        dbRequestAnalyzerManager.finish();
        return result;
    }

}
