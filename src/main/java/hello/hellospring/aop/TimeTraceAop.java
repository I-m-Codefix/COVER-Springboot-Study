package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 이걸 적어줘야 AOP 사용 가능
@Component // @Bean으로 대체 가능 - Config에 선언하는 걸 더 선호.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")        // 타케팅할 수 있음. 의미 : 패키지 하위에  다적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed(); // Object result = joinPoint.proceed(); + return result; - inline으로 합친 것
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
                    "ms");
        }
    }
}
