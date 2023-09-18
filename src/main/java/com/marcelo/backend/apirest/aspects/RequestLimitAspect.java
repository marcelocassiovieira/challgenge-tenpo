package com.marcelo.backend.apirest.aspects;

import com.marcelo.backend.apirest.exception.RequestLimitException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestLimitAspect {
    private static final int MAX_REQUESTS = 3;
    private static final long TIME_FRAME = 60 * 1000; // 60 segundos en milisegundos
    private static long lastResetTime = System.currentTimeMillis();
    private static int requestCount = 0;

    @Before("@annotation(IRequestLimit)")
    public void limitRequests(JoinPoint joinPoint) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastResetTime > TIME_FRAME) {
            requestCount = 0;
            lastResetTime = currentTime;
        }

        if (requestCount >= MAX_REQUESTS) {
            throw new RequestLimitException(HttpStatus.TOO_MANY_REQUESTS, "Excedido el límite de solicitudes por minuto");
        }
        requestCount++;
    }

}
