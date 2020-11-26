package advice.throwsadvice;

public class ErrorBean {
    public void errorProneMethod() throws Exception {
        throw new Exception("Generic Exception");
    }

    public void otherErrorProneMethod() throws IllegalAccessException {
        throw new IllegalArgumentException("IllegalArgument Exception");
    }
}
