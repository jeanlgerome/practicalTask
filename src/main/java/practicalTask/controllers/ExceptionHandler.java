package practicalTask.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import practicalTask.utils.response.ErrorContainer;

import javax.validation.ValidationException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс обработки исключений в контроллере
 */
@ControllerAdvice
public class ExceptionHandler {

    private Logger logger = Logger.getLogger(Advice.class.toString());

    /**
     * Обрабатывает исключение и помещает его сообщение в ErrorContainer, который затем
     * возвращает пользователю в составе ResponseEntity
     *
     * @param ex - обрабатываемое  исключение
     * @return ответ пользователю с сообщением об ошибке IllegalArgumentException
     * @see ErrorContainer
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
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
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public final ResponseEntity handleUnknownExceptions(Exception ex) {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        logger.log(Level.WARNING, String.format("Invalid input parameters: %s. UUID: %s", ex.getMessage(), uuid), ex);
        String exceptionResponse = String.format("Внутренняя ошибка сервера. UUID: %s", uuid);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorContainer(exceptionResponse));
    }
}
