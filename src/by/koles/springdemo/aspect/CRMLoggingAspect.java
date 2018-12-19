package by.koles.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	private Logger logger = Logger.getLogger(CRMLoggingAspect.class.getName());
	
	@Pointcut("execution(* by.koles.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* by.koles.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* by.koles.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		logger.info("in @Before: calling method - " + joinPoint.getSignature().toShortString());
		Object[] args = joinPoint.getArgs();
		for(Object temp : args) {
			logger.info(">>> argument: " + temp + " <<<<<");
		}
	}
	
	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		logger.info("in @AfterReturning: calling method - " + joinPoint.getSignature().toShortString());
		logger.info(">>>>> result: " + result + " <<<<<");
	}
	
}


















