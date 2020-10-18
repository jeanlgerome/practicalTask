package practicalTask.utils.response;

/**
 * Контейнер для данных, в нем информация отправляется пользователю
 */
public class DataContainer {

    private final Object data;

    public DataContainer(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

}
