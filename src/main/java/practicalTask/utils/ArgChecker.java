package practicalTask.utils;

import java.time.LocalDate;

/**
 * Util класс.
 * Здесь реализуются проверки параметров на корректность.
 * Эти проверки повсеместно используются в публичных методах
 *
 * @version 1.0
 */

public class ArgChecker {

    /**
     * Возвращает проверяемый str если значение корректно.
     *
     * @param str проверяемый параметр
     * @param argName имя параметра, используется для информативности при составлении сообщения Exception
     * @return проверяемый параметр
     * @throws IllegalArgumentException, если проверка не пройдена.
     */
    public static String requireNonBlank(String str, String argName) {
        if (str == null || str.trim().isEmpty() || str.length() < Constants.MIN_VARCHAR_LENGTH || str.length() > Constants.MAX_VARCHAR_LENGTH) {
            throw new IllegalArgumentException(String.format("%s  string must not be correct", argName));
        }
        return str;
    }

    /**
     * Возвращает проверяемый number ессли значение корректно.
     *
     * @param number проверяемый параметр
     * @param argName имя параметра, используется для информативности при составлении сообщения Exception
     * @return проверяемый параметр
     * @throws IllegalArgumentException, если проверка не пройдена.
     */
    public static Long requireNonNull(Long number, String argName) {
        if (number == null) {
            throw new IllegalArgumentException(String.format("%s  long number must not be null", argName));
        }
        return number;
    }

    /**
     * Возвращает проверяемый object если значение корректно.
     *
     * @param object проверяемый параметр
     * @param argName имя параметра, используется для информативности при составлении сообщения Exception
     * @return проверяемый параметр
     * @throws IllegalArgumentException, если проверка не пройдена.
     */
    public static Object requireNonNull(Object object, String argName) {
        if (object == null) {
            throw new IllegalArgumentException(String.format("%s  object must not be null", argName));
        }
        return object;
    }

    /**
     * Возвращает проверяемый date если значение корректно.
     * (Этот метод не доделан, т.к. неясно, какие ограничения должны быть у даты)
     *
     * @param date проверяемый параметр
     * @param argName имя параметра, используется для информативности при составлении сообщения Exception
     * @return проверяемый параметр
     * @throws IllegalArgumentException, если проверка не пройдена.
     */
    public static LocalDate requireCorrectDate(LocalDate date, String argName) {
        return date;
    }
}
