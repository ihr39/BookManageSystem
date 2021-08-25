package org.spring.my.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component //spring이 자동객체 생성
@Aspect
public class AopAdvice {
	//표현형: 반환형 * 어떤 것이든 상관 없다 패키지 안의 *의 모든 것에 적용을 해주겠다
	//반환형/클래스명/메소드명//매개변수
	@Before("execution(* org.spring.my.service.*.*(..))")
	public void startLogService(JoinPoint jp) { //적용받는 매개 변수
		System.out.println("--------------------");
		System.out.println(jp.getSignature().toLongString());//시그니쳐는 위치 tostring으로 확인
		System.out.println("매개변수:" + Arrays.toString(jp.getArgs()));
		System.out.println("--------------------");
	}
	@Before("execution(* org.spring.my.controller.*.*(..))")
	public void startLogController(JoinPoint jp) { //적용받는 매개 변수
		System.out.println("--------------------");
		System.out.println(jp.getSignature().toLongString());//시그니쳐는 위치 tostring으로 확인
		System.out.println("매개변수:" + Arrays.toString(jp.getArgs()));
		System.out.println("--------------------");
	}
	@AfterReturning(pointcut = "execution(* org.spring.my.service.*.*(..))",returning ="ob")//정상 수행 후 리턴 된 값을 찍겠다
	public void afterLogService(JoinPoint jp, Object ob) {
		//받은 객체를 오브젝트에 담아야함
		if(ob != null) {
			System.out.println("--------------------");
			System.out.println(jp.getSignature().toLongString());//시그니쳐는 위치 tostring으로 확인
			System.out.println("리턴값:" + ob.toString());
			System.out.println("--------------------");
		}
	}
	@AfterReturning(pointcut = "execution(* org.spring.my.controller.*.*(..))",returning ="ob")//정상 수행 후 리턴 된 값을 찍겠다
	public void afterLogController(JoinPoint jp, Object ob) {
		//받은 객체를 오브젝트에 담아야함
		if(ob != null) {
			System.out.println("--------------------");
			System.out.println(jp.getSignature().toLongString());//시그니쳐는 위치 tostring으로 확인
			System.out.println("리턴값:" + ob.toString());
			System.out.println("--------------------");
		}
	}
	//예외가 발생
	@AfterThrowing(pointcut = "execution(* org.spring.my.service.*.*(..))", throwing ="eob")
	public void exceptionLog(JoinPoint jp, Exception eob) {
		System.out.println("--------------------");
		System.out.println(jp.getSignature().toLongString());//시그니쳐는 위치 tostring으로 확인
		System.out.println("예외발생:" + eob.toString());
		System.out.println("--------------------");
	}
	
	//모듈 소요시간 체크
	@Around("execution(* org.spring.my.service.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-----------소요시간-----------");
		//시작시간
		System.out.println(pjp.getSignature().toLongString());
		long starttime=System.currentTimeMillis();//시작 시간 찍기
		Object result=pjp.proceed();//실제 실행해야할 메소드 실행
		long endtime =System.currentTimeMillis();//종료시간 찍기
		System.out.println("시간:" +(endtime-starttime));//걸린 시간
		System.out.println("-----------소요시간 끝---------");
		return result;//호출한 메소드에 전달
	}
}
