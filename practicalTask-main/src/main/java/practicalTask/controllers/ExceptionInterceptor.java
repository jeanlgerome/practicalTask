package practicalTask.controllers;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import practicalTask.utils.response.ErrorContainer;

import javax.validation.ConstraintViolationException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс обработки исключений в контроллере
 */
@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    private Logger logger = Logger.getLogger(ExceptionInterceptor.class.toString());

    /**
     * Обрабатывает исключение и помещает его сообщение в ErrorContainer, который затем
     * возвращает пользователю в составе ResponseEntity
     *
     * @param ex - обрабатываемое  исключение
     * @return ответ пользователю с сообщением об ошибке ConstraintViolationException
     * @see ErrorContainer
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity handleConstraintViolationExceptions(
            ConstraintViolationException ex) {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        logger.log(Level.WARNING, String.format("Invalid input parameters: %s. UUID: %s", ex.getMessage(), uuid), ex);
        String exceptionResponse = String.format("Invalid input parameters: %s. UUID: %s", ex.getMessage(), uuid);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorContainer(exceptionResponse));
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
    public final ResponseEntity handleIllegalArgumentExceptions(IllegalArgumentException ex) {
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
     * @return ответ пользователю с сообщением об ошибке HibernateException
     * @see ErrorContainer
     */
    @ExceptionHandler(HibernateException.class)
    public final ResponseEntity handleHibernateExceptions(HibernateException ex) {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        String exceptionResponse = String.format("Внутренняя ошибка сервера. UUID: %s", uuid);
        logger.log(Level.WARNING, String.format("Invalid input parameters: %s. UUID: %s", ex.getMessage(), uuid), ex);
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
