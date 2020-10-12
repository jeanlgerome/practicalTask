package practicalTask.utils.response;

public class ErrorContainer {

    private final String error;

    public ErrorContainer(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
