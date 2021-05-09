package io.kimmking.rpcfx.demo.consumer.aspect;

import io.kimmking.rpcfx.annotation.Reference;
import io.kimmking.rpcfx.client.Rpcfx;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author switch
 * @since 2021/5/8
 */
@Aspect
@Component
public class ReferencedAspect {

    @Before("execution(* io.kimmking.rpcfx.demo.consumer..*.*(..))")
    public void setReference(JoinPoint joinPoint) throws Exception {
        Object target = joinPoint.getTarget();
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            Reference reference = field.getAnnotation(Reference.class);
            if (reference != null) {
                field.setAccessible(true);
                if (field.get(target) == null) {
                    field.set(target, Rpcfx.create(field.getType(), reference.url()));
                }
            }
        }
    }
}

