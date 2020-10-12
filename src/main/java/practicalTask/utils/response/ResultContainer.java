package practicalTask.utils.response;

public class ResultContainer {

    private final String result;

    public ResultContainer(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static class DataContainer {

        private final Object data;

        public DataContainer(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

    }
}
