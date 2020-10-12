package practicalTask.controllers;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import practicalTask.utils.response.ErrorContainer;

import javax.validation.ConstraintViolationException;

/**
 * Класс обработки исключений в контроллере
 */
@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    /**
     * Обрабатывает исключение и помещает его сообщение в ErrorContainer, который затем
     * возвращает пользователю в составе ResponseEntity
     *
     * @param ex - обрабатываемое  исключение
     * @return ответ пользователю с сообщением об ошибке ConstraintViolationException
     * @see ErrorContainer
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationExceptions(
            ConstraintViolationException ex) {
        String exceptionResponse = String.format("Invalid input parameters: %s", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorContainer(exceptionResponse));
    }

    /**
     * Обрабатывает исключение и помещает его сообщение в ErrorContainer, который затем
     * возвращает пользователю в составе ResponseEntity
     *
     * @param ex - обрабатываемое  исключение
     * @return ответ пользователю с сообщением об ошибке IllegalArgumentException
     * @see ErrorContainer
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentExceptions(IllegalArgumentException ex) {
        String exceptionResponse = String.format("IllegalArgumentException: %s", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorContainer(exceptionResponse));
    }

    /**
     * Обрабатывает исключение и помещает его сообщение в ErrorContainer, который затем
     * возвращает пользователю в составе ResponseEntity
     *
     * @param ex - обрабатываемое  исключение
     * @return ответ пользователю с сообщением об ошибке HibernateException
     * @see ErrorContainer
     */
    @ExceptionHandler(HibernateException.class)
    public final ResponseEntity<Object> handleHibernateExceptions(HibernateException ex) {
        String exceptionResponse = String.format("HibernateException: %s", ex.getMessage());
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
    public final ResponseEntity<Object> handleUnknownExceptions(Exception ex) {
        String exceptionResponse = String.format("Unknown Exception: %s", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorContainer(exceptionResponse));
    }
}
