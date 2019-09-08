package com.mum.ecommerce.aop;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {

    private static Logger logger=LogManager.getLogger(LogManager.class.getName());

    @Before("execution(* com.mum.ecommerce.service.*(..))")
    public void LogBefore(JoinPoint joinPoint){
        System.out.println("Welcome to our ecommerce next level");
        logger.warn(" @Before getting method: "+ joinPoint.getSignature().getName());
    }
    @After("execution(* com.mum.ecommerce.service.*(..))")
    public void LogAfter(JoinPoint joinPoint){
        System.out.println("Welcome to our ecommerce next level");
        logger.warn(" @After returning method: "+ joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.mum.ecommerce.service.*(..))", returning = "sss")
    public void LogAfter(JoinPoint joinPoint, String sss){
        logger.warn(joinPoint.getSignature().getName()+"successfully returned"+ sss);
    }

    @AfterThrowing(value = "execution(* com.mum.ecommerce.service.*(..))", throwing = "ex")
    public void LogAfter(JoinPoint joinPoint, Exception ex){

        logger.warn(joinPoint.getSignature().getName()+ " method throws" +  ex.getClass().getName());
    }


}
