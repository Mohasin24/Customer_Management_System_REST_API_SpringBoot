package com.customer_management.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectController
{
    @Before("execution(* com.customer_management.controller.ApplicationController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Inside the " + joinPoint.getSignature().toShortString());
    }

    @After("execution(* com.customer_management.controller.ApplicationController.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Exiting from " + joinPoint.getSignature().toShortString());
    }


    @AfterThrowing(pointcut ="execution(* com.customer_management.controller.ApplicationController.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println("After Throwing " + joinPoint.getSignature().toShortString() + ", Exception: " + exception.getMessage());
    }

    @Around("execution(* com.customer_management.controller.ApplicationController.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Inside the @Around aspect before execution " + joinPoint.getSignature().toShortString());
        Object result = joinPoint.proceed();
        System.out.println("Inside the @Around aspect after execution " + joinPoint.getSignature().toShortString());
        return result;
    }

    @AfterReturning(pointcut = "execution(* com.customer_management.controller.ApplicationController.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Returning result from " + joinPoint.getSignature().toShortString() + ", Result: " + result);
    }
}
