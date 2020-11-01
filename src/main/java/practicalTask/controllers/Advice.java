package practicalTask.controllers;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import practicalTask.utils.response.DataContainer;
import practicalTask.utils.response.ErrorContainer;
import practicalTask.utils.response.ResultContainer;

/**
 * Класс оборачивает данные, которые возвращает контроллер
 */
@ControllerAdvice
public class Advice extends ResponseEntityExceptionHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * Оборачивает данные в DataContainer.
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ErrorContainer) {
            return o;
        } else if (methodParameter.getParameterType().isAssignableFrom(void.class)) {
            return new ResultContainer("success");
        }
        return new DataContainer(o);
    }
}
