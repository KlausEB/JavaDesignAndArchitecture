package com.epam.architecture.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class LoggingAspect {

    @Pointcut("within(com.epam.architecture.controllers.service.*)")
    public void forAnyControllers() {
    }

    @Before("forAnyControllers()")
    public void methodWasCalled(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication != null ? authentication.getName() : "Unauthorized user";
        log.info(
                "Method "
                        + joinPoint.getSignature().getName()
                        + " was called by "
                        + userName);
    }

    @AfterReturning(pointcut = "forAnyControllers()", returning = "givenValue")
    public void returnMethod(JoinPoint joinPoint, Object givenValue) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication != null ? authentication.getName() : "Unauthorized user";
        String resultMessage = givenValue != null ? " got correct result" : " got incorrect result";
        log.info(
                userName
                        + resultMessage
                        + " from "
                        + joinPoint.getSignature().getName());
    }

    @AfterThrowing("forAnyControllers()")
    public void returnMethod(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication != null ? authentication.getName() : "Unauthorized user";
        log.info(
                userName
                        + " got incorrect result");
    }
}
