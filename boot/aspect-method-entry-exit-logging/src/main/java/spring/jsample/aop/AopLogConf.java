package spring.jsample.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@ConditionalOnProperty(name = "aop.logging")
/**
 * If the value of the key "aop.logging" is true in application.properties file,
 * then only this configuration will be enabled.
 *
 */
public class AopLogConf {

	private static final String ADVICE_EXP = "execution(* spring.jsample..*.*(..))";

	/**
	 * @Before annotation means execute aspect before execution of matched method
	 * 
	 */
	@Before(ADVICE_EXP)
	public void before(JoinPoint joinPoint) {

		System.out.println("@Before " + joinPoint);
	}

	/**
	 * @After annotation means execute aspect when the method executed successfully
	 *        or it threw an exception.
	 * 
	 */
	@After(ADVICE_EXP)
	public void after(JoinPoint joinPoint) {
		System.out.println("@After " + joinPoint);
	}

	/**
	 * @AfterReturning annotation means execute aspect when the method executed
	 *                 successfully
	 */
	@AfterReturning(ADVICE_EXP)
	public void afterReturning(JoinPoint joinPoint) {
		System.out.println("@AfterReturning " + joinPoint);
	}

	/**
	 * @AfterThrowing annotation means execute aspect when the method threw an
	 *                exception.
	 */
	@AfterThrowing(ADVICE_EXP)
	public void afterThrowing(JoinPoint joinPoint) {
		System.out.println("@AfterThrowing " + joinPoint);
	}

	/**
	 * @throws Throwable
	 * @AfterThrowing annotation means execute aspect when the method threw an
	 *                exception.
	 */
	@Around(ADVICE_EXP)
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

		StringBuilder entryMsg = new StringBuilder("Entering method ").append(joinPoint.getSignature());
		entryMsg.append(" with parameters ").append(Arrays.asList(joinPoint.getArgs()));

		System.out.println(entryMsg);

		Object returnValue;
		try {
			returnValue = joinPoint.proceed();
			StringBuilder exitMsg = new StringBuilder("Existing method ").append(joinPoint.getSignature());
			exitMsg.append(" with return type ").append(returnValue);

			System.out.println(exitMsg);

		} catch (Throwable e) {
			StringBuilder exitMsg = new StringBuilder("Existing method ").append(joinPoint.getSignature());
			exitMsg.append(" with exception ").append(e);
			System.out.println(exitMsg);
			throw e;
		}
		return returnValue;
	}
}