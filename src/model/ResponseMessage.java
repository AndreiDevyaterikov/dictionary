package model;

public class ResponseMessage <T>{
    private final T answer;


    public ResponseMessage(T answer) {
        this.answer = answer;
    }

    public T getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "answer=" + answer +
                '}';
    }
}
