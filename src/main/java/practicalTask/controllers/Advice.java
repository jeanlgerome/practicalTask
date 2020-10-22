package practicalTask.controllers;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import practicalTask.utils.response.DataContainer;
import practicalTask.utils.response.ErrorContainer;

import javax.validation.ValidationException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс обработки исключений в контроллере
 */
@ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler implements ResponseBodyAdvice {

    private Logger logger = Logger.getLogger(ControllerAdvice.class.toString());

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * Оборачивает данные в DataContainer. Нужно ли оборачивать не данные, а результат операции?
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return new DataContainer(o);
    }

    /**
     * Обрабатывает исключение и помещает его сообщение в ErrorContainer, который затем
     * возвращает пользователю в составе ResponseEntity
     *
     * @param ex - обрабатываемое  исключение
     * @return ответ пользователю с сообщением об ошибке IllegalArgumentException
     * @see ErrorContainer
     */
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity handleValidationException(ValidationException ex) {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        logger.log(Level.WARNING, String.format("Invalid input parameters: %s. UUID: %s", ex.getMessage(), uuid), ex);
        String exceptionResponse = String.format("IllegalArgumentException: %s. UUID: %s", ex.getMessage(), uuid);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorContainer(exceptionResponse));
    }

    /**
     * Обрабатывает исключение и помещает его сообщение в ErrorContainer, который затем
     * возвращает пользователю в составе ResponseEntity
     *
     * @param ex - обрабатываемое  исключение
     * @return ответ пользователю с сообщением об ошибке UnknownException
     * @see ErrorContainer
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleUnknownExceptions(Exception ex) {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        logger.log(Level.WARNING, String.format("Invalid input parameters: %s. UUID: %s", ex.getMessage(), uuid), ex);
        String exceptionResponse = String.format("Внутренняя ошибка сервера. UUID: %s", uuid);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorContainer(exceptionResponse));
    }
}
